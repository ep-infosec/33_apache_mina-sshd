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
package org.apache.sshd.common.io;

import org.apache.sshd.common.future.SshFuture;
import org.apache.sshd.common.future.VerifiableFuture;
import org.apache.sshd.common.util.buffer.Buffer;

/**
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public interface IoReadFuture extends SshFuture<IoReadFuture>, VerifiableFuture<IoReadFuture> {

    /**
     * Retrieves the buffer data was read into.
     *
     * @return the buffer, {@code null} if {@link #isDone()} {@code == false}
     */
    Buffer getBuffer();

    /**
     * Retrieves the number of bytes read.
     *
     * @return The number of bytes read, or -1 if the source of the read has been exhausted (is at EOF), or zero if the
     *         read is not done yet ({@link #isDone()} {@code == false})
     */
    int getRead();

    /**
     * Returns the cause of the read failure. An {@link java.io.EOFException} indicates that nothing was read because
     * the source of the read is exhausted.
     *
     * @return {@code null} if the read operation is not finished yet, or if the read attempt is successful (use
     *         {@link #isDone()} to distinguish between the two).
     */
    Throwable getException();
}
