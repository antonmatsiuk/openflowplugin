/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba;

import org.opendaylight.openflowjava.beba.api.BebaExtensionCodecRegistrator;
import org.opendaylight.openflowjava.beba.codec.message.BebaMessageCodecs;
import org.opendaylight.openflowjava.beba.codec.message.PkttmpModCodec;
import com.google.common.base.Preconditions;

public class BebaExtensionsRegistrator implements AutoCloseable {

    private final BebaExtensionCodecRegistrator registrator;


    public BebaExtensionsRegistrator(BebaExtensionCodecRegistrator registrator) {
        this.registrator = Preconditions.checkNotNull(registrator);
    }

    public void registerBebaExtensions() {
        registrator.registerExperimenterMessageDeserializer(PkttmpModCodec.DESERIALIZER_KEY, BebaMessageCodecs.PKTTMP_MOD_CODEC);

    }

    public void unregisterExtensions() {
    }

    @Override
    public void close() throws Exception {
        unregisterExtensions();
    }

}
