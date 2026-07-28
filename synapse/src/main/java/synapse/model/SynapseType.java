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
package synapse.model;

import java.util.Locale;

/**
 * Types supported by a Synapse property mediator.
 */
public enum SynapseType {
    STRING,
    INTEGER,
    INT,
    LONG,
    SHORT,
    BOOLEAN,
    DOUBLE,
    FLOAT,
    OM,
    JSON;

    /**
     * Parses a Synapse type name, defaulting missing or unrecognized values to
     * {@link #STRING}.
     *
     * @param value Synapse type name
     * @return corresponding Synapse type
     */
    public static SynapseType from(String value) {
        if (value == null || value.isBlank()) {
            return STRING;
        }
        try {
            return valueOf(value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ignored) {
            return STRING;
        }
    }
}
