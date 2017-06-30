/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba.api.impl;

import io.netty.buffer.ByteBuf;
import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterInstructionDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev150225.instruction.container.instruction.choice.experimenter.id._case.Experimenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstructionDeserializer implements OFDeserializer<Experimenter> {

    private static final Logger LOG = LoggerFactory.getLogger(InstructionDeserializer.class);

    public static final ExperimenterInstructionDeserializerKey DESERIALIZER_KEY = new  ExperimenterInstructionDeserializerKey (
            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID);


    public InstructionDeserializer() {}

    @Override
    public Experimenter deserialize(ByteBuf message) {
        int startPossition = message.readerIndex();
        long experimenterId = message.readUnsignedInt();
        if (BebaConstants.BEBA_VENDOR_ID != experimenterId) {
            throw new IllegalStateException("Experimenter ID is not Beba vendor id but is " + experimenterId);
        }
        long instrtype = message.readUnsignedShort();
        OFDeserializer<Experimenter> instructionsDeserializer = BebaExtensionCodecRegistratorImpl.getInstructionsDeserializer(instrtype);
        if (instructionsDeserializer == null) {
            LOG.error("No deserializer was found for key {}", instrtype);
            return null;
        }
        message.readerIndex(startPossition);
        return instructionsDeserializer.deserialize(message);
    }
}
