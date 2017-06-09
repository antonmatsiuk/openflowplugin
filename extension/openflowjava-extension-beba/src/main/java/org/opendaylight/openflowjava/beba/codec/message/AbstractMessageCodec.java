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
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ExperimenterId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;

public abstract class AbstractMessageCodec implements OFSerializer<ExperimenterDataOfChoice>, OFDeserializer<ExperimenterDataOfChoice> {

    protected final static ExperimenterId getExperimenterId(){
        return BebaConstants.BEBA_EXPERIMENTER_ID;
    }

    protected static ExperimenterIdTypeDeserializerKey getCodecDeserializerKey(long exptype){
        return new ExperimenterIdTypeDeserializerKey(
                EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, exptype,
                ExperimenterDataOfChoice.class);
    }

    protected static ExperimenterIdTypeSerializerKey getCodecSerializerKey(long exptype){
        return new ExperimenterIdTypeSerializerKey(
                EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, exptype,
                ExperimenterDataOfChoice.class);
    }

    protected ByteBuf skipHeader(final ByteBuf message) {
        // size of xid
        message.skipBytes(EncodeConstants.SIZE_OF_LONG_IN_BYTES );
        // expId
        message.skipBytes(EncodeConstants.SIZE_OF_LONG_IN_BYTES );
        // expType
        message.skipBytes(EncodeConstants.SIZE_OF_LONG_IN_BYTES );
        return message;
    }
}
