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

package org.apache.sshd.common.util.io.input;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@code /dev/null} implementation - always open
 *
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public class EmptyInputStream extends InputStream {
    public static final EmptyInputStream DEV_NULL = new EmptyInputStream();

    public EmptyInputStream() {
        super();
    }

    @Override
    public int read() throws IOException {
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return -1;
    }

    @Override
    public long skip(long n) throws IOException {
        return 0L;
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    @Override
    public synchronized void mark(int readlimit) {
        throw new UnsupportedOperationException(
                "mark(" + readlimit + ") called despite the fact that markSupported=" + markSupported());
    }

    @Override
    public synchronized void reset() throws IOException {
        // ignored
    }
}
