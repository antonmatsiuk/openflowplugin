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
import org.opendaylight.openflowjava.beba.api.impl.BebaExtensionCodecRegistratorImpl;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterInstructionSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev150225.instruction.container.instruction.choice.experimenter.id._case.Experimenter;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.instruction.rev170307.BebaExperimenterInstr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstructionSerializer implements OFSerializer<Experimenter> {

    private static final Logger LOG = LoggerFactory.getLogger(InstructionSerializer.class);

    public static final ExperimenterInstructionSerializerKey SERIALIZER_KEY = new  ExperimenterInstructionSerializerKey (
            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID);


    public InstructionSerializer() {}

    @Override
    public void serialize(Experimenter input, ByteBuf outBuffer) {
        Long experimenterId = input.getExperimenterId().getValue();
        if (BebaConstants.BEBA_VENDOR_ID != experimenterId) {
            throw new IllegalStateException("Experimenter ID is not Beba vendor id but is " + experimenterId);
        }
        BebaExperimenterInstr bebaExperimenter = (BebaExperimenterInstr) input;
        Long instrtype = bebaExperimenter.getBebaInstrType();

        OFSerializer<Experimenter> instructionTypeSerializer = BebaExtensionCodecRegistratorImpl.getInstructionTypeSerializer(instrtype);

        if (instructionTypeSerializer == null) {
            LOG.error("NoSserializer was found for instrtype {}", instrtype);
            return;
        }
        instructionTypeSerializer.serialize(input, outBuffer);
    }
}
