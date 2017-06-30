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
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterInstructionDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterInstructionSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev150225.instruction.container.instruction.choice.experimenter.id._case.Experimenter;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.instruction.rev170307.BebaExperimenterInstr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InSpCodec extends AbstractInstructionCodec {


    public static final Long DESERIALIZER_KEY = BebaConstants.OFPIT_IN_SWITCH_PKT_GEN;
    public static final Long SERIALIZER_KEY = BebaConstants.OFPIT_IN_SWITCH_PKT_GEN;

    private static final Logger LOG = LoggerFactory.getLogger(InSpCodec.class);

    @Override
    public void serialize(Experimenter input, ByteBuf outBuffer) {
        LOG.error("Serialize error - Unimplemented serializer");
        //TODO
        BebaExperimenterInstr bebaexp = (BebaExperimenterInstr) input;

    }

    @Override
    public Experimenter deserialize(ByteBuf message) {
        LOG.error("Deserialize error - Unimplemented deserializer");
        return null;
    }
}
