/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowplugin.extension.vendor.beba.convertor.message;

import com.google.common.base.Preconditions;
import org.opendaylight.openflowjava.beba.api.BebaConstants;
import org.opendaylight.openflowplugin.extension.api.ConvertorMessageFromOFJava;
import org.opendaylight.openflowplugin.extension.api.ConvertorMessageToOFJava;
import org.opendaylight.openflowplugin.extension.api.exception.ConversionException;
import org.opendaylight.openflowplugin.extension.api.path.MessagePath;
import org.opendaylight.openflowplugin.extension.vendor.beba.BebaExtensionProvider;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ExperimenterId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.experimenter.core.ExperimenterDataOfChoice;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowjava.beba.rev170307.experimenter.input.experimenter.data.of.choice.MsgPkttmpModCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowplugin.experimenter.types.rev151020.experimenter.core.message.ExperimenterMessageOfChoice;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflowplugin.extension.beba.message.rev170307.send.experimenter.input.experimenter.message.of.choice.PkttmpModCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PkttmpConvertor implements ConvertorMessageToOFJava <ExperimenterMessageOfChoice, ExperimenterDataOfChoice>,
                            ConvertorMessageFromOFJava <ExperimenterDataOfChoice, MessagePath>  {

    private static final Logger LOG = LoggerFactory
            .getLogger(PkttmpConvertor.class);

    @Override
    public ExperimenterMessageOfChoice convert(ExperimenterDataOfChoice input, MessagePath path)
            throws ConversionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExperimenterDataOfChoice convert(ExperimenterMessageOfChoice BebaMsgArg) {
        Preconditions.checkArgument(BebaMsgArg instanceof PkttmpModCase);
        PkttmpModCase pkttmpModCase = (PkttmpModCase)BebaMsgArg;
        MsgPkttmpModCaseBuilder msgPkttmpModCaseBuilder = new MsgPkttmpModCaseBuilder();
        msgPkttmpModCaseBuilder.setMsgPkttmpMod(pkttmpModCase.getMsgPkttmpMod());
        LOG.info("Convert ExperimenterMessageOfChoice -> ExperimenterDataOfChoice PkttmpId: {} command: {}",
                msgPkttmpModCaseBuilder.getMsgPkttmpMod().getPkttmpid(), pkttmpModCase.getMsgPkttmpMod().getCommand().getName());
        return msgPkttmpModCaseBuilder.build();
    }

    @Override
    public ExperimenterId getExperimenterId() {
        return BebaConstants.BEBA_EXPERIMENTER_ID;
    }

    @Override
    public long getType() {
        return BebaConstants.PKTTMP_CODEC_ID;
    }

}
