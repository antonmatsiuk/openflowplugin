/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.instruction;

import io.netty.buffer.ByteBuf;
import java.util.List;
import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.SerializerRegistry;
import org.opendaylight.openflowjava.protocol.api.extensibility.SerializerRegistryInjector;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.openflowjava.protocol.impl.util.InstructionConstants;
import org.opendaylight.openflowjava.protocol.impl.util.ListSerializer;
import org.opendaylight.openflowjava.protocol.impl.util.TypeKeyMaker;
import org.opendaylight.openflowjava.protocol.impl.util.TypeKeyMakerFactory;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev150225.instruction.container.instruction.choice.experimenter.id._case.Experimenter;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.action.rev150203.actions.grouping.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ExperimenterId;


public abstract class AbstractInstructionCodec implements OFSerializer<Experimenter>, OFDeserializer<Experimenter>,
                SerializerRegistryInjector {

    protected final static ExperimenterId getExperimenterId(){
        return BebaConstants.BEBA_EXPERIMENTER_ID;
    }

    private SerializerRegistry registry;

    private static final TypeKeyMaker<Action> ACTION_KEY_MAKER =
            TypeKeyMakerFactory.createActionKeyMaker(EncodeConstants.OF13_VERSION_ID);

    protected void writeActions(final List<Action> actions, final ByteBuf outBuffer, int startIndex) {
        int lengthIndex = outBuffer.writerIndex();
        outBuffer.writeShort(EncodeConstants.EMPTY_LENGTH);
        outBuffer.writeZero(InstructionConstants.PADDING_IN_ACTIONS_INSTRUCTION);
        ListSerializer.serializeList(actions, ACTION_KEY_MAKER, getRegistry(), outBuffer);
        int instructionLength = outBuffer.writerIndex() - startIndex;
        outBuffer.setShort(lengthIndex, instructionLength);
    }

    protected SerializerRegistry getRegistry() {
        return registry;
    }

    protected ByteBuf skipHeader(final ByteBuf message) {
        // expId
        message.skipBytes(EncodeConstants.SIZE_OF_LONG_IN_BYTES);
        return message;
    }

    @Override
    public void serialize(Experimenter input, ByteBuf outBuffer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void injectSerializerRegistry(final SerializerRegistry serializerRegistry) {
        registry = serializerRegistry;
    }
}
