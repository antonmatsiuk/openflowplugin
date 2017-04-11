/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.api;

import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;


public interface BebaExtensionCodecRegistrator {

    void registerExperimenterMessageTypeDeserializer(ExperimenterIdTypeDeserializerKey key, OFDeserializer<ExperimenterDataOfChoice> deserializer);

    void unregisterExperimenterMessageTypeDeserializer(ExperimenterIdTypeDeserializerKey key);

    void registerExperimenterMessageTypeSerializer(ExperimenterIdTypeSerializerKey key, OFSerializer<ExperimenterDataOfChoice>  serializer);

    void unregisterExperimenterMessageTypeSerializer(ExperimenterIdTypeSerializerKey key);


    /*
    void registerActionDeserializer(BebaActionDeserializerKey key, OFDeserializer<Action> deserializer);

    void unregisterActionDeserializer(BebaActionDeserializerKey key);

    void registerActionSerializer(BebaActionSerializerKey key, OFSerializer<Action> serializer);

    void unregisterActionSerializer(BebaActionSerializerKey key);
    */
}
