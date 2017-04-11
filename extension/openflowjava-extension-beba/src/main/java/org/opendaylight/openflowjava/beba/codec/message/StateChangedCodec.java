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

public class StateChangedCodec extends AbstractMessageCodec {

    public static final long EXPTYPE = BebaConstants.STATE_CHANGED_CODEC_ID; // OFPT_EXP_PKTTMP_MOD
    public static final ExperimenterIdTypeDeserializerKey  DESERIALIZER_KEY = new ExperimenterIdTypeDeserializerKey(
            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, EXPTYPE,
            ExperimenterDataOfChoice.class);
    public static final ExperimenterIdTypeSerializerKey SERIALIZER_KEY = new ExperimenterIdTypeSerializerKey(
            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, EXPTYPE,
            ExperimenterDataOfChoice.class);

    private static final Logger LOG = LoggerFactory.getLogger(StateChangedCodec.class);


    @Override
    public void serialize(ExperimenterDataOfChoice input, ByteBuf outBuffer) {
        LOG.error("Serialize error - Unimplemented serializer");
        //TODO

    }

    @Override
    public ExperimenterDataOfChoice deserialize(ByteBuf message) {
        LOG.error("Deserialize error - Unimplemented derializer");
        return null;
    }
}
