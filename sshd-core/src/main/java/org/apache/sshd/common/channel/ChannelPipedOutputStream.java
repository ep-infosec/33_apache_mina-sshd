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
package org.apache.sshd.common.channel;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channel;

/**
 * TODO Add javadoc
 *
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public class ChannelPipedOutputStream extends OutputStream implements Channel {

    private final ChannelPipedSink sink;
    private final byte[] b = new byte[1];
    private boolean closed;

    public ChannelPipedOutputStream(ChannelPipedSink sink) {
        this.sink = sink;
    }

    @Override
    public void write(int i) throws IOException {
        synchronized (b) {
            b[0] = (byte) i;
            write(b, 0, 1);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (!isOpen()) {
            throw new IOException("write(len=" + len + ") Stream has been closed");
        }
        sink.receive(b, off, len);
    }

    @Override
    public boolean isOpen() {
        return !closed;
    }

    @Override
    public void flush() throws IOException {
        // Nothing to do since we hand off all data immediately to the sink. Do not throw an exception if we're already
        // closed; see https://github.com/apache/mina-sshd/issues/266
    }

    @Override
    public void close() throws IOException {
        if (isOpen()) {
            try {
                sink.eof();
            } finally {
                closed = true;
            }
        }
    }
}
