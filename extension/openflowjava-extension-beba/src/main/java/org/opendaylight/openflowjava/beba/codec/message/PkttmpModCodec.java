/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.message;

import io.netty.buffer.ByteBuf;
import org.opendaylight.openflowjava.beba.api.BebaMessageDeserializerKey;
import org.opendaylight.openflowjava.beba.api.BebaMessageSerializerKey;
import org.opendaylight.openflowjava.beba.api.impl.ExperimenterMessageDeserializer;
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

    //public static final int LENGTH = 18; //???
    public static final long EXPTYPE = 1; // OFPT_EXP_PKTTMP_MOD
    public static final BebaMessageSerializerKey SERIALIZER_KEY = new BebaMessageSerializerKey(
            EncodeConstants.OF13_VERSION_ID, PkttmpModCodec.class);
    public static final BebaMessageDeserializerKey DESERIALIZER_KEY = new BebaMessageDeserializerKey(
            EncodeConstants.OF13_VERSION_ID, EXPTYPE);

    private static final Logger LOG = LoggerFactory.getLogger(PkttmpModCodec.class);

    //ExperimenterIdSerializerKey SERIALIZER_KEY1 = new ExperimenterIdSerializerKey

    @Override
    public void serialize(ExperimenterDataOfChoice input, ByteBuf outBuffer) {
        // TODO Auto-generated method stub
        MsgPkttmpModCase msgPkttmpModCase = (MsgPkttmpModCase) input;
        MsgPkttmpMod msgPkttmpMod = msgPkttmpModCase.getMsgPkttmpMod();
        PkttmpModCommand command = msgPkttmpMod.getCommand();

        LOG.info("Serialize ExperimenterDataOfChoice PkttmpId: {} command: {}",
                msgPkttmpMod.getPkttmpid(), command.getName());
        outBuffer.writeByte(command.getIntValue());
        outBuffer.writeByte(0); //pad??
        outBuffer.writeInt(msgPkttmpMod.getPkttmpid().intValue());

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
    public ExperimenterDataOfChoice deserialize(ByteBuf message) {
        message = skipHeader(message);
        MsgPkttmpModCaseBuilder msgPkttmpModCaseBuilder = new MsgPkttmpModCaseBuilder();
        MsgPkttmpModBuilder msgPkttmpModBuilder = new MsgPkttmpModBuilder();
        PkttmpModCommand command = PkttmpModCommand.forValue(message.readUnsignedShort());
        msgPkttmpModBuilder.setCommand(command);
        msgPkttmpModBuilder.setPkttmpid(message.readLong());
        switch (command){
        case OFPSCADDPKTTMP:
            AddPkttmpBuilder addPkttmpBuilder = new AddPkttmpBuilder();
            int datalen = message.readableBytes();
            addPkttmpBuilder.setData(message.readBytes(datalen).array());
        case OFPSCDELPKTTMP:
            break;
        default:
            LOG.error("Serialize error - unknown MsgPkttmpMod Command: {}", command.getIntValue());
            break;
        }
        msgPkttmpModCaseBuilder.setMsgPkttmpMod(msgPkttmpModBuilder.build());
        return msgPkttmpModCaseBuilder.build();
    }

}
