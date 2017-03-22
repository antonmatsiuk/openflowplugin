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
import org.opendaylight.openflowjava.beba.api.BebaMessageDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey ;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.action.rev150203.actions.grouping.ActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ExperimenterId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.ExperimenterMessage;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.ExperimenterMessageBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExperimenterMessageDeserializer implements OFDeserializer<ExperimenterDataOfChoice>   {

    private static final Logger LOG = LoggerFactory.getLogger(ExperimenterMessageDeserializer.class);

    public static final ExperimenterIdDeserializerKey  OF13_DESERIALIZER_KEY = new ExperimenterIdDeserializerKey  (
            EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, ExperimenterMessage.class);

    private final short version;

    /**
     * @param version protocol wire version
     */
    public ExperimenterMessageDeserializer(short version) {
        this.version = version;
    }
    @Override
    public ExperimenterDataOfChoice deserialize(ByteBuf message) {
        //Check what's in the buffer here. It should be ExperimenterDataOfChoice ?
        //Do we need this method or getMessageDeserializer(key) happens in the core library??
        final long xid = message.readUnsignedInt();
        final long expId = message.readUnsignedInt();
        final long expType = message.readUnsignedInt();

        if (BebaConstants.BEBA_VENDOR_ID != expId) {
            throw new IllegalStateException("Experimenter ID is not Beba vendor id but is " + expId);
        }

        BebaMessageDeserializerKey key = new BebaMessageDeserializerKey(version, expType);
        OFDeserializer<ExperimenterDataOfChoice> messageDeserializer = BebaExtensionCodecRegistratorImpl.getMessageDeserializer(key);
        if (messageDeserializer == null) {
            LOG.info("No deserializer was found for key {}", key);
            return null;
        }

        final ExperimenterDataOfChoice bebaData = messageDeserializer.deserialize(message);
        return bebaData;
    }
}
