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

package org.apache.sshd.common.channel.exception;

import java.io.IOException;

import org.apache.sshd.common.channel.ChannelIdentifier;

/**
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public abstract class SshChannelException extends IOException implements ChannelIdentifier {
    private static final long serialVersionUID = 7355720478400167933L;

    private final long channelId;

    protected SshChannelException(long channelId, String message) {
        this(channelId, message, null);
    }

    protected SshChannelException(long channelId, Throwable cause) {
        this(channelId, cause.getMessage(), cause);
    }

    protected SshChannelException(long channelId, String message, Throwable cause) {
        super(message, cause);
        this.channelId = channelId;
    }

    @Override
    public long getChannelId() {
        return channelId;
    }
}
