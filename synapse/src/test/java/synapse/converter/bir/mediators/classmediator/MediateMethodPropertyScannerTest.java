/*
 *  Copyright (c) 2026, WSO2 LLC. (http://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package synapse.converter.bir.mediators.classmediator;

import org.testng.Assert;
import org.testng.annotations.Test;
import synapse.converter.ConversionContext;
import synapse.converter.ScopeContext;
import synapse.converter.SequenceContext;
import synapse.converter.bir.mediators.classmediator.source.JavaSource;

import java.util.Set;

public class MediateMethodPropertyScannerTest {

    private static final String CLASS_NAME = "test.SampleMediator";

    @Test
    public void testReadNotRegistered() {
        ScopeContext context = newContext();
        JavaSource source = mediator("""
                String lang = (String) mc.getProperty("lang");
                """);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertTrue(context.shared().properties().isEmpty());
        Assert.assertTrue(context.shared().unsupported().isEmpty());
    }

    @Test
    public void testLiteralWrite() {
        ScopeContext context = newContext();
        JavaSource source = mediator("""
                mc.setProperty("greeting", "Hello");
                """);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertEquals(context.shared().properties().get("greeting").types(), Set.of("string"));
    }

    @Test
    public void testTracedLocalWrite() {
        ScopeContext context = newContext();
        JavaSource source = mediator("""
                int count = 5;
                mc.setProperty("count", count);
                """);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertEquals(context.shared().properties().get("count").types(), Set.of("int"));
    }

    @Test
    public void testUntraceableWrite() {
        ScopeContext context = newContext();
        JavaSource source = mediator("""
                mc.setProperty("out", someHelper());
                """);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertEquals(context.shared().properties().get("out").types(), Set.of("anydata"));
    }

    @Test
    public void testTracedMethodReturnWrite() {
        ScopeContext context = newContext();
        JavaSource source = new JavaSource(CLASS_NAME, """
                package test;
                import org.apache.synapse.MessageContext;
                public class SampleMediator {
                    private int buildCount(MessageContext mc) {
                        return 5;
                    }
                    public boolean mediate(MessageContext mc) {
                        mc.setProperty("count", buildCount(mc));
                        return true;
                    }
                }
                """, JavaSource.Origin.SOURCE_FILE);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertEquals(context.shared().properties().get("count").types(), Set.of("int"));
    }

    @Test
    public void testNestedTouch() {
        ScopeContext context = newContext();
        JavaSource source = mediator("""
                String lang = (String) mc.getProperty("lang");
                if ("en".equals(lang)) {
                    mc.setProperty("greeting", "Hello");
                }
                """);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertFalse(context.shared().properties().containsKey("lang"));
        Assert.assertEquals(context.shared().properties().get("greeting").types(), Set.of("string"));
    }

    @Test
    public void testNoMediateMethod() {
        ScopeContext context = newContext();
        JavaSource source = new JavaSource(CLASS_NAME, """
                package test;
                public class SampleMediator {
                    public boolean mediate(Object notAMessageContext) {
                        notAMessageContext.getProperty("lang");
                        return true;
                    }
                }
                """, JavaSource.Origin.SOURCE_FILE);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertTrue(context.shared().properties().isEmpty());
        Assert.assertEquals(context.shared().unsupported().size(), 1);
    }

    @Test
    public void testUnparsableSource() {
        ScopeContext context = newContext();
        JavaSource source = new JavaSource(CLASS_NAME, "not valid java {{{", JavaSource.Origin.SOURCE_FILE);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertTrue(context.shared().properties().isEmpty());
        Assert.assertEquals(context.shared().unsupported().size(), 1);
    }

    @Test
    public void testUnresolvedPropertyName() {
        ScopeContext context = newContext();
        JavaSource source = mediator("""
                mc.setProperty(anotherKey, "Hello");
                """);

        MediateMethodPropertyScanner.scan(source, CLASS_NAME, context);

        Assert.assertTrue(context.shared().properties().isEmpty());
        Assert.assertEquals(context.shared().unsupported().size(), 1);
    }

    private static JavaSource mediator(String body) {
        String source = """
                package test;
                import org.apache.synapse.MessageContext;
                public class SampleMediator {
                    public boolean mediate(MessageContext mc) {
                        %s
                        return true;
                    }
                }
                """.formatted(body);
        return new JavaSource(CLASS_NAME, source, JavaSource.Origin.SOURCE_FILE);
    }

    private static ScopeContext newContext() {
        return new SequenceContext(new ConversionContext());
    }
}
