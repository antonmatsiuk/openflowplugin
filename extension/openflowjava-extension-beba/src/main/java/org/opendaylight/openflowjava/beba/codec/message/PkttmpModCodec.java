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
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.PkttmpModCommand;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.experimenter.input.experimenter.data.of.choice.MsgPkttmpModCase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.experimenter.input.experimenter.data.of.choice.MsgPkttmpModCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.msg.pkttmp.mod.grouping.MsgPkttmpMod;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.msg.pkttmp.mod.grouping.MsgPkttmpModBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.pkttmp.cmd.grouping.cmd.choice.AddPkttmpCase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.pkttmp.cmd.grouping.cmd.choice.add.pkttmp._case.AddPkttmp;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.pkttmp.cmd.grouping.cmd.choice.add.pkttmp._case.AddPkttmpBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PkttmpModCodec extends AbstractMessageCodec {

    public static final long EXPTYPE = BebaConstants.PKTTMP_CODEC_ID; // OFPT_EXP_STATE_CHANGED
//    public static final ExperimenterIdTypeDeserializerKey  DESERIALIZER_KEY = new ExperimenterIdTypeDeserializerKey(
//            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, EXPTYPE,
//            ExperimenterDataOfChoice.class);
    public static final ExperimenterIdTypeDeserializerKey  DESERIALIZER_KEY = getCodecDeserializerKey(EXPTYPE);
    public static final ExperimenterIdTypeSerializerKey SERIALIZER_KEY = getCodecSerializerKey(EXPTYPE);

    private static final Logger LOG = LoggerFactory.getLogger(PkttmpModCodec.class);

    @Override
    public void serialize(ExperimenterDataOfChoice input, ByteBuf outBuffer) {
        // TODO Auto-generated method stub
        MsgPkttmpModCase msgPkttmpModCase = (MsgPkttmpModCase) input;
        MsgPkttmpMod msgPkttmpMod = msgPkttmpModCase.getMsgPkttmpMod();
        PkttmpModCommand command = msgPkttmpMod.getCommand();

        LOG.info("Serialize ExperimenterDataOfChoice PkttmpId: {} command: {}",
                msgPkttmpMod.getPkttmpid(), command.getName());
        outBuffer.writeByte(command.getIntValue());
        outBuffer.writeByte(0); //pad
        outBuffer.writeInt(msgPkttmpMod.getPkttmpid().intValue());
        outBuffer.writeBytes(new byte[4]);

        switch (command){
        case OFPSCADDPKTTMP:
            AddPkttmpCase addPkttmpcase = (AddPkttmpCase) msgPkttmpMod.getCmdChoice();
            AddPkttmp addPkttmp = addPkttmpcase.getAddPkttmp();
            outBuffer.writeBytes(addPkttmp.getData());
            break;
        case OFPSCDELPKTTMP:
            break;
        default:
            LOG.error("Serialize error - unknown MsgPkttmpMod Command: {}", command.getIntValue());
            break;
        }
    }

    @Override
    //Never expected from the switch!
    public ExperimenterDataOfChoice deserialize(ByteBuf message) {
        message = skipHeader(message); //???
        MsgPkttmpModCaseBuilder msgPkttmpModCaseBuilder = new MsgPkttmpModCaseBuilder();
        MsgPkttmpModBuilder msgPkttmpModBuilder = new MsgPkttmpModBuilder();
        PkttmpModCommand command = PkttmpModCommand.forValue(message.readUnsignedShort());
        msgPkttmpModBuilder.setCommand(command);
        message.skipBytes(EncodeConstants.SIZE_OF_BYTE_IN_BYTES);
        msgPkttmpModBuilder.setPkttmpid(message.readLong());
        switch (command){
        case OFPSCADDPKTTMP:
            AddPkttmpBuilder addPkttmpBuilder = new AddPkttmpBuilder();
            int datalen = message.readableBytes();
            addPkttmpBuilder.setData(message.readBytes(datalen).array());
        case OFPSCDELPKTTMP:
            break;
        default:
            LOG.error("Deserialize error - unknown MsgPkttmpMod Command: {}", command.getIntValue());
            break;
        }
        LOG.info("Deserialize ExperimenterDataOfChoice PkttmpId: {} command: {} data: {}",
                msgPkttmpModBuilder.getPkttmpid(), command.getName());
        msgPkttmpModCaseBuilder.setMsgPkttmpMod(msgPkttmpModBuilder.build());
        return msgPkttmpModCaseBuilder.build();
    }

}
