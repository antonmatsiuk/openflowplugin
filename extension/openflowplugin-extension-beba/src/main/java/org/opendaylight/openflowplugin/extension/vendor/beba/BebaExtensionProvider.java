/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowplugin.extension.vendor.beba;

import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdSerializerKey;
import org.opendaylight.openflowjava.protocol.api.keys.ExperimenterIdTypeSerializerKey;
import org.opendaylight.openflowjava.protocol.api.util.EncodeConstants;
import org.opendaylight.openflowplugin.extension.api.ConvertorMessageFromOFJava;
import org.opendaylight.openflowplugin.extension.api.ConvertorMessageToOFJava;
import org.opendaylight.openflowplugin.extension.api.ExtensionConverterRegistrator;
import org.opendaylight.openflowplugin.extension.api.TypeVersionKey;
import org.opendaylight.openflowplugin.extension.vendor.beba.convertor.message.PkttmpConvertor;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.experimenter.input.experimenter.data.of.choice.MsgPkttmpModCase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowplugin.experimenter.types.rev151020.experimenter.core.message.ExperimenterMessageOfChoice;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowplugin.extension.beba.message.rev170307.send.experimenter.input.experimenter.message.of.choice.PkttmpModCase;
import org.opendaylight.openflowplugin.extension.api.path.MessagePath;
import org.opendaylight.yangtools.concepts.ObjectRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class BebaExtensionProvider implements AutoCloseable {

    private static final Logger LOG = LoggerFactory
            .getLogger(BebaExtensionProvider.class);

    private ExtensionConverterRegistrator extensionConverterRegistrator;
    private Set<ObjectRegistration<?>> registrations;

    private final static PkttmpConvertor PKTTMP_CONVERTOR = new PkttmpConvertor();

    @Override
    public void close() {
        for (AutoCloseable janitor : registrations) {
            try {
                janitor.close();
            } catch (Exception e) {
                LOG.warn("closing of extension converter failed", e);
            }
        }
        extensionConverterRegistrator = null;
    }

    /**
     * @param extensionConverterRegistrator
     */
    public void setExtensionConverterRegistrator(
            final ExtensionConverterRegistrator extensionConverterRegistrator) {
        this.extensionConverterRegistrator = extensionConverterRegistrator;
    }

    /**
     * register appropriate converters
     */
    public void registerConverters() {
        Preconditions.checkNotNull(extensionConverterRegistrator);
        registrations = new HashSet<>();
        registerExperimenterMessage13(PkttmpModCase.class, PKTTMP_CONVERTOR); //MD-SAL -> Java
        registerExperimenterMessage13(MsgPkttmpModCase.class, PKTTMP_CONVERTOR); //Java -> MD-SAL
    }

    /**
     * @param expMessageType
     * @param messageConvertor
     */
    private void registerExperimenterMessage13(
            Class<? extends ExperimenterMessageOfChoice> expMessageType,
            ConvertorMessageToOFJava<ExperimenterMessageOfChoice, ExperimenterDataOfChoice> messageConvertor) {
        TypeVersionKey<ExperimenterMessageOfChoice> key = new TypeVersionKey<>(expMessageType, EncodeConstants.OF13_VERSION_ID);
        registrations.add(extensionConverterRegistrator.registerMessageConvertor(key, messageConvertor));
    }

    /**
     * @param dataofChoice
     * @param messageConvertor
     */
    private void registerExperimenterMessage13(
            Class<? extends ExperimenterDataOfChoice> dataofChoice,
            ConvertorMessageFromOFJava<ExperimenterDataOfChoice, MessagePath> messageConvertor) {
        ExperimenterIdSerializerKey<?> key = new ExperimenterIdSerializerKey(EncodeConstants.OF13_VERSION_ID, BebaConstants.BEBA_VENDOR_ID, dataofChoice);
        registrations.add(extensionConverterRegistrator.registerMessageConvertor(key, messageConvertor));
    }

}
