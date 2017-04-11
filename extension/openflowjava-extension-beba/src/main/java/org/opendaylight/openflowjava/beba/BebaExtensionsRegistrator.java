/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba;

import com.google.common.base.Preconditions;
import org.opendaylight.openflowjava.beba.api.BebaExtensionCodecRegistrator;
import org.opendaylight.openflowjava.beba.codec.message.BebaMessageCodecs;
import org.opendaylight.openflowjava.beba.codec.message.EventModCodec;
import org.opendaylight.openflowjava.beba.codec.message.FlowNotifCodec;
import org.opendaylight.openflowjava.beba.codec.message.PkttmpModCodec;
import org.opendaylight.openflowjava.beba.codec.message.StateChangedCodec;
import org.opendaylight.openflowjava.beba.codec.message.StateModCodec;

public class BebaExtensionsRegistrator implements AutoCloseable {

    private final BebaExtensionCodecRegistrator registrator;


    public BebaExtensionsRegistrator(BebaExtensionCodecRegistrator registrator) {
        this.registrator = Preconditions.checkNotNull(registrator);
    }

    public void registerBebaExtensions() {
        registrator.registerExperimenterMessageTypeDeserializer(PkttmpModCodec.DESERIALIZER_KEY, BebaMessageCodecs.PKTTMP_MOD_CODEC);
        registrator.registerExperimenterMessageTypeSerializer(PkttmpModCodec.SERIALIZER_KEY, BebaMessageCodecs.PKTTMP_MOD_CODEC);
        registrator.registerExperimenterMessageTypeDeserializer(EventModCodec.DESERIALIZER_KEY, BebaMessageCodecs.EVENT_MOD_CODEC);
        registrator.registerExperimenterMessageTypeSerializer(EventModCodec.SERIALIZER_KEY, BebaMessageCodecs.EVENT_MOD_CODEC);
        registrator.registerExperimenterMessageTypeDeserializer(StateChangedCodec.DESERIALIZER_KEY, BebaMessageCodecs.STATE_CHANGED_CODEC);
        registrator.registerExperimenterMessageTypeSerializer(StateChangedCodec.SERIALIZER_KEY, BebaMessageCodecs.STATE_CHANGED_CODEC);
        registrator.registerExperimenterMessageTypeDeserializer(StateModCodec.DESERIALIZER_KEY, BebaMessageCodecs.STATE_MOD_CODEC);
        registrator.registerExperimenterMessageTypeSerializer(StateModCodec.SERIALIZER_KEY, BebaMessageCodecs.STATE_MOD_CODEC);
        registrator.registerExperimenterMessageTypeDeserializer(FlowNotifCodec.DESERIALIZER_KEY, BebaMessageCodecs.FLOW_NOTIF_CODEC);
        registrator.registerExperimenterMessageTypeSerializer(FlowNotifCodec.SERIALIZER_KEY, BebaMessageCodecs.FLOW_NOTIF_CODEC);
    }

    public void unregisterExtensions() {
        registrator.unregisterExperimenterMessageTypeSerializer(PkttmpModCodec.SERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeDeserializer(PkttmpModCodec.DESERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeSerializer(EventModCodec.SERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeDeserializer(EventModCodec.DESERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeSerializer(StateChangedCodec.SERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeDeserializer(StateChangedCodec.DESERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeSerializer(StateModCodec.SERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeDeserializer(StateModCodec.DESERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeSerializer(FlowNotifCodec.SERIALIZER_KEY);
        registrator.unregisterExperimenterMessageTypeDeserializer(FlowNotifCodec.DESERIALIZER_KEY);

    }

    @Override
    public void close() throws Exception {
        unregisterExtensions();
    }

}
