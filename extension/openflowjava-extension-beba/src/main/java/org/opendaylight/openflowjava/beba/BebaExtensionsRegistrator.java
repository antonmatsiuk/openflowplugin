/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba;

import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowjava.beba.api.BebaExtensionCodecRegistrator;
import org.opendaylight.openflowjava.beba.codec.message.BebaMessageCodecs;
import org.opendaylight.openflowjava.beba.codec.message.PkttmpModCodec;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.ExperimenterMessage;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import com.google.common.base.Preconditions;

public class BebaExtensionsRegistrator implements AutoCloseable {

    private final BebaExtensionCodecRegistrator registrator;


    public BebaExtensionsRegistrator(BebaExtensionCodecRegistrator registrator) {
        this.registrator = Preconditions.checkNotNull(registrator);
    }

    public void registerBebaExtensions() {
        //registrator.registerExperimenterMessageDeserializer(PkttmpModCodec.DESERIALIZER_KEY, BebaMessageCodecs.PKTTMP_MOD_CODEC);
        //registrator.registerExperimenterMessageDeserializer(PkttmpModCodec.DESERIALIZER_KEY, BebaMessageCodecs.PKTTMP_MOD_CODEC);

        ExperimenterIdTypeDeserializerKey keydeser = new ExperimenterIdTypeDeserializerKey(EncodeConstants.OF13_VERSION_ID,
                BebaConstants.BEBA_VENDOR_ID, BebaConstants.PKTTMP_CODEC_ID, ExperimenterDataOfChoice.class);
        registrator.registerExperimenterIdMessageDeserializer(keydeser, BebaMessageCodecs.PKTTMP_MOD_CODEC);


        ExperimenterIdTypeSerializerKey keyser = new ExperimenterIdTypeSerializerKey(EncodeConstants.OF13_VERSION_ID,
                BebaConstants.BEBA_VENDOR_ID, BebaConstants.PKTTMP_CODEC_ID, ExperimenterDataOfChoice.class);
        registrator.registerExperimenterIdMessageSerializer(keyser, BebaMessageCodecs.PKTTMP_MOD_CODEC);
    }

    public void unregisterExtensions() {
    }

    @Override
    public void close() throws Exception {
        unregisterExtensions();
    }

}
