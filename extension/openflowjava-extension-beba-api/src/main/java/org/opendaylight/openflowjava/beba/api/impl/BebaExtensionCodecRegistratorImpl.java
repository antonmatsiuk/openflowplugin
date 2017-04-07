/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.api.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.opendaylight.openflowjava.beba.api.BebaActionDeserializerKey;
import org.opendaylight.openflowjava.beba.api.BebaExtensionCodecRegistrator;
import org.opendaylight.openflowjava.beba.api.BebaMessageDeserializerKey;
import org.opendaylight.openflowjava.beba.api.BebaMessageSerializerKey;
import org.opendaylight.openflowjava.beba.api.BebaUtil;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer;
import org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdSerializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeDeserializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.openflowjava.protocol.spi.connection.SwitchConnectionProvider;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.action.rev150203.actions.grouping.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;

public class BebaExtensionCodecRegistratorImpl implements BebaExtensionCodecRegistrator, AutoCloseable {

    //private static final Map<BebaActionDeserializerKey, OFDeserializer<Action>> actionDeserializers = new ConcurrentHashMap<>();
    private static final Map<BebaMessageDeserializerKey, OFDeserializer<ExperimenterDataOfChoice>> messageDeserializers = new ConcurrentHashMap<>();

    private final List<SwitchConnectionProvider> providers;

    /**
     * @param providers
     */
    public BebaExtensionCodecRegistratorImpl(List<SwitchConnectionProvider> providers) {
        this.providers = providers;

        ExperimenterMessageDeserializer of13MessageDeserializer  = new ExperimenterMessageDeserializer(EncodeConstants.OF13_VERSION_ID);
        registerExperimenterMessageDeserializer(ExperimenterMessageDeserializer.OF13_DESERIALIZER_KEY, of13MessageDeserializer);


        //ActionDeserializer of10ActionDeserializer = new ActionDeserializer(EncodeConstants.OF10_VERSION_ID);
        //ActionDeserializer of13ActionDeserializer = new ActionDeserializer(EncodeConstants.OF13_VERSION_ID);
        //registerActionDeserializer(ActionDeserializer.OF10_DESERIALIZER_KEY, of10ActionDeserializer);
        //registerActionDeserializer(ActionDeserializer.OF13_DESERIALIZER_KEY, of13ActionDeserializer);
    }

    @Override
    public void registerExperimenterIdMessageDeserializer(ExperimenterIdTypeDeserializerKey key, OFDeserializer<ExperimenterDataOfChoice> deserializer) {
        for (SwitchConnectionProvider provider : providers) {
            provider.registerExperimenterMessageDeserializer(key, deserializer);
        }
    }

    @Override
    public void registerExperimenterMessageDeserializer(ExperimenterIdDeserializerKey key, OFDeserializer<ExperimenterDataOfChoice> deserializer) {
        for (SwitchConnectionProvider provider : providers) {
            provider.registerExperimenterMessageDeserializer(key, deserializer);
        }
    }

    @Override
    public void registerExperimenterIdMessageSerializer(ExperimenterIdTypeSerializerKey key, OFSerializer<ExperimenterDataOfChoice>  serializer) {
        for (SwitchConnectionProvider provider : providers) {
            provider.registerExperimenterMessageSerializer(key, serializer);
        }
    }
    @Override
   public void registerExperimenterMessageSerializer(ExperimenterIdSerializerKey key, OFSerializer<ExperimenterDataOfChoice>  serializer) {
        for (SwitchConnectionProvider provider : providers) {
            provider.registerExperimenterMessageSerializer(key, serializer);
        }
    }

    @Override
   public void unregisterDeserializer(ExperimenterDeserializerKey key) {
        for (SwitchConnectionProvider provider : providers) {
            provider.unregisterDeserializer(key);
        }
    }
    @Override
   public void unregisterSerializer(ExperimenterSerializerKey key) {
        for (SwitchConnectionProvider provider : providers) {
            provider.unregisterSerializer(key);
        }
    }



    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerExperimenterMessageDeserializer(BebaMessageDeserializerKey key,
            OFDeserializer<ExperimenterDataOfChoice> deserializer) {
            messageDeserializers.put(key, deserializer);

    }

    @Override
    public void registerExperimenterMessageSerializer(BebaMessageSerializerKey key,
            OFSerializer<ExperimenterDataOfChoice> serializer) {
            registerExperimenterMessageSerializer(BebaUtil.createOfJavaKeyFrom(key), serializer);
    }

    @Override
    public void unregisterExperimenterMessageDeserializer(BebaMessageDeserializerKey key) {
        messageDeserializers.remove(key);

    }

    @Override
    public void unregisterExperimenterMessageSerializer(BebaMessageSerializerKey key) {
        unregisterSerializer(BebaUtil.createOfJavaKeyFrom(key));

    }

    static OFDeserializer<ExperimenterDataOfChoice> getMessageDeserializer(BebaMessageDeserializerKey key) {
        return messageDeserializers.get(key);
    }

//    /*
//     * (non-Javadoc)
//     *
//     * @see org.opendaylight.openflow.extension.beba.api.
//     * BebaExtensionCodecRegistrator
//     * #registerActionDeserializer(org.opendaylight
//     * .openflow.extension.beba.api.BebaActionDeserializerKey,
//     * org.opendaylight.openflowjava.protocol.api.extensibility.OFDeserializer)
//     */
//    @Override
//    public void registerActionDeserializer(BebaActionDeserializerKey key, OFDeserializer<Action> deserializer) {
//        actionDeserializers.put(key, deserializer);
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.opendaylight.openflow.extension.beba.api.
//     * BebaExtensionCodecRegistrator
//     * #unregisterActionDeserializer(org.opendaylight
//     * .openflow.extension.beba.api.BebaActionDeserializerKey)
//     */
//    @Override
//    public void unregisterActionDeserializer(BebaActionDeserializerKey key) {
//        actionDeserializers.remove(key);
//    }
//
//    static OFDeserializer<Action> getActionDeserializer(BebaActionDeserializerKey key) {
//        return actionDeserializers.get(key);
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.opendaylight.openflow.extension.beba.api.
//     * BebaExtensionCodecRegistrator
//     * #registerActionSerializer(org.opendaylight.
//     * openflow.extension.beba.api.BebaActionSerializerKey,
//     * org.opendaylight.openflowjava.protocol.api.extensibility.OFSerializer)
//     */
//    @Override
//    public void registerActionSerializer(BebaActionSerializerKey key, OFSerializer<Action> serializer) {
//        registerActionSerializer(BebaUtil.createOfJavaKeyFrom(key), serializer);
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.opendaylight.openflow.extension.beba.api.
//     * BebaExtensionCodecRegistrator
//     * #unregisterActionSerializer(org.opendaylight
//     * .openflow.extension.beba.api.BebaActionSerializerKey)
//     */
//    @Override
//    public void unregisterActionSerializer(BebaActionSerializerKey key) {
//        unregisterSerializer(BebaUtil.createOfJavaKeyFrom(key));
//    }

}
