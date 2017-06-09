/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.message;

import io.netty.buffer.ByteBuf;
import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EventModCodec extends AbstractMessageCodec {

    public static final long EXPTYPE = BebaConstants.EVENT_MOD_CODEC_ID; //OFPT_EXP_EVENT_MO
    public static final ExperimenterIdTypeDeserializerKey  DESERIALIZER_KEY = getCodecDeserializerKey(EXPTYPE);
    public static final ExperimenterIdTypeSerializerKey SERIALIZER_KEY = getCodecSerializerKey(EXPTYPE);

    private static final Logger LOG = LoggerFactory.getLogger(EventModCodec.class);


    @Override
    public void serialize(ExperimenterDataOfChoice input, ByteBuf outBuffer) {
        LOG.error("Serialize error - Unimplemented serializer");
        //TODO

    }

    @Override
    public ExperimenterDataOfChoice deserialize(ByteBuf message) {
        LOG.error("Deserialize error - Unimplemented deserializer");
        return null;
    }
}
