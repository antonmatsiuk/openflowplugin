/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.message;

import io.netty.buffer.ByteBuf;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;


public class EventModCodec extends AbstractMessageCodec {

    @Override
    public ExperimenterDataOfChoice deserialize(ByteBuf message) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void serialize(ExperimenterDataOfChoice input, ByteBuf outBuffer) {
        // TODO Auto-generated method stub

    }

}
