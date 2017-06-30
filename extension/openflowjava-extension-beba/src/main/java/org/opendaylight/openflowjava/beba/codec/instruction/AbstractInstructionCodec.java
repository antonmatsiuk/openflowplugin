/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.instruction;

import io.netty.buffer.ByteBuf;
import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterInstructionDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterInstructionSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev150225.instruction.container.instruction.choice.experimenter.id._case.Experimenter;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ExperimenterId;


public abstract class AbstractInstructionCodec implements OFSerializer<Experimenter>, OFDeserializer<Experimenter> {

    protected final static ExperimenterId getExperimenterId(){
        return BebaConstants.BEBA_EXPERIMENTER_ID;
    }


    protected ByteBuf skipHeader(final ByteBuf message) {
        // expId
        message.skipBytes(EncodeConstants.SIZE_OF_LONG_IN_BYTES);
        return message;
    }
}
