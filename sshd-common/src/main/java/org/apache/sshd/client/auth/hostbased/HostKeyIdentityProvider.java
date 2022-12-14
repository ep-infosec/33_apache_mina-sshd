/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.sshd.client.auth.hostbased;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.sshd.common.session.SessionContext;
import org.apache.sshd.common.util.GenericUtils;

/**
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
@FunctionalInterface
public interface HostKeyIdentityProvider {
    /**
     * @param  session                  The {@link SessionContext} for invoking this load command - may be {@code null}
     *                                  if not invoked within a session context (e.g., offline tool).
     * @return                          The host keys as a {@link java.util.Map.Entry} of key + certificates (which can
     *                                  be {@code null}/empty)
     * @throws IOException              If failed to load the keys
     * @throws GeneralSecurityException If failed to parse the keys
     */
    Iterable<? extends Map.Entry<KeyPair, List<X509Certificate>>> loadHostKeys(SessionContext session)
            throws IOException, GeneralSecurityException;

    static Iterator<? extends Map.Entry<KeyPair, List<X509Certificate>>> iteratorOf(
            SessionContext session, HostKeyIdentityProvider provider)
            throws IOException, GeneralSecurityException {
        return GenericUtils.iteratorOf((provider == null) ? null : provider.loadHostKeys(session));
    }

    static HostKeyIdentityProvider wrap(KeyPair... pairs) {
        return wrap(GenericUtils.asList(pairs));
    }

    static HostKeyIdentityProvider wrap(Iterable<KeyPair> pairs) {
        return session -> GenericUtils.wrapIterable(pairs,
                kp -> new SimpleImmutableEntry<>(kp, Collections.<X509Certificate> emptyList()));
    }
}
