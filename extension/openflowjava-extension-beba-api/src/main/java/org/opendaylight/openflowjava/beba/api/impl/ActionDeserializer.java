/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba.api.impl;

import io.netty.buffer.ByteBuf;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.action.rev150203.actions.grouping.Action;

public class ActionDeserializer implements OFDeserializer<Action> {

    @Override
    public Action deserialize(ByteBuf message) {
        // TODO Auto-generated method stub
        return null;
    }

//    private static final Logger LOG = LoggerFactory.getLogger(ActionDeserializer.class);
//
//    public static final ExperimenterActionDeserializerKey OF13_DESERIALIZER_KEY = new ExperimenterActionDeserializerKey(
//            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID);
//    public static final ExperimenterActionDeserializerKey OF10_DESERIALIZER_KEY = new ExperimenterActionDeserializerKey(
//            EncodeConstants.OF10_VERSION_ID, BebaConstants.BEBA_VENDOR_ID);
//
//    private final short version;
//
//    /**
//     * @param version protocol wire version
//     */
//    public ActionDeserializer(short version) {
//        this.version = version;
//    }
//
//    @Override
//    public Action deserialize(ByteBuf message) {
//        int startPossition = message.readerIndex();
//        // size of experimenter type
//        message.skipBytes(EncodeConstants.SIZE_OF_SHORT_IN_BYTES);
//        // size of length
//        message.skipBytes(EncodeConstants.SIZE_OF_SHORT_IN_BYTES);
//        long experimenterId = message.readUnsignedInt();
//        if (BebaConstants.BEBA_VENDOR_ID != experimenterId) {
//            throw new IllegalStateException("Experimenter ID is not Beba vendor id but is " + experimenterId);
//        }
//        int subtype = message.readUnsignedShort();
//        BebaActionDeserializerKey key = new BebaActionDeserializerKey(version, subtype);
//        OFDeserializer<Action> actionDeserializer = BebaExtensionCodecRegistratorImpl.getActionDeserializer(key);
//        if (actionDeserializer == null) {
//            LOG.info("No deserializer was found for key {}", key);
//            return null;
//        }
//        message.readerIndex(startPossition);
//        return actionDeserializer.deserialize(message);
//    }


}
