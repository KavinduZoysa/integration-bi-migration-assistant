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
package mule.v4.dataweave.converter;

import mule.TestUtils;
import mule.v4.Context;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static mule.v4.dataweave.converter.DWReader.getFunctionStatement;

public class TestDWScriptRule {

    private static final String PARSE_FAILURE_MARKER = "DATAWEAVE PARSING FAILED.";

    @Test(dataProvider = "provideRejectedScripts")
    public void testScriptWithoutHeaderOrBodyIsRejected(String script) {
        Assert.assertTrue(convert(script).contains(PARSE_FAILURE_MARKER),
                "expected the script to be rejected: '" + script + "'");
    }

    @DataProvider(name = "provideRejectedScripts")
    public Object[][] provideRejectedScripts() {
        return new Object[][]{
                {""},
                {"\n\n   \n"},
                {"---"}
        };
    }

    @Test(dataProvider = "provideAcceptedScripts")
    public void testScriptWithHeaderOrBodyIsAccepted(String script) {
        Assert.assertFalse(convert(script).contains(PARSE_FAILURE_MARKER),
                "expected the script to be accepted: '" + script + "'");
    }

    @DataProvider(name = "provideAcceptedScripts")
    public Object[][] provideAcceptedScripts() {
        return new Object[][]{
                // header only, as in a module script
                {"var greeting = \"hello\"\n"},
                // body only, as in an inline expression
                {"[1, 2]\n"},
                // separator without a header
                {"---\n[1, 2]\n"},
                // header without a body
                {"%dw 2.0\noutput application/json\n---\n"},
                {"%dw 2.0\noutput application/json\n---\n{ a: 1 }\n"}
        };
    }

    private static String convert(String script) {
        Context ctx = TestUtils.createMockContext();
        DWContext dwContext = new DWContext(ctx, new ArrayList<>());
        try {
            return getFunctionStatement(script, null, dwContext, ctx, "result", "dwTransform");
        } catch (DWCodeGenException e) {
            throw new RuntimeException(e);
        }
    }
}
