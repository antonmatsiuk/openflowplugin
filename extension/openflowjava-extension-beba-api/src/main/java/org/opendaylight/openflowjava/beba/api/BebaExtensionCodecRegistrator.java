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
import org.opendaylight.openflowjava.protocol.api.keys.MatchEntryDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.MatchEntrySerializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterSerializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdSerializerKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.ExperimenterMessage;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.action.rev150203.actions.grouping.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.oxm.rev150225.MatchField;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.oxm.rev150225.OxmClassBase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.oxm.rev150225.match.entries.grouping.MatchEntry;


public interface BebaExtensionCodecRegistrator {

    void registerExperimenterMessageDeserializer(BebaMessageDeserializerKey key, OFDeserializer<ExperimenterDataOfChoice> deserializer);

    void registerExperimenterMessageSerializer(BebaMessageSerializerKey key, OFSerializer<ExperimenterDataOfChoice> serializer);

    void unregisterExperimenterMessageDeserializer(BebaMessageDeserializerKey key);

    void unregisterExperimenterMessageSerializer(BebaMessageSerializerKey key);

    /*
    void registerActionDeserializer(BebaActionDeserializerKey key, OFDeserializer<Action> deserializer);

    void unregisterActionDeserializer(BebaActionDeserializerKey key);

    void registerActionSerializer(BebaActionSerializerKey key, OFSerializer<Action> serializer);

    void unregisterActionSerializer(BebaActionSerializerKey key);
    */
}
