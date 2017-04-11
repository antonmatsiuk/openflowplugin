/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba.api;

import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ExperimenterId;

public final class BebaConstants {

    public static final Long BEBA_VENDOR_ID = 3199909562L;
    public final static ExperimenterId BEBA_EXPERIMENTER_ID = new ExperimenterId(BEBA_VENDOR_ID);

    //enum ofp_exp_messages
    public static final Long STATE_MOD_CODEC_ID = 0L;
    public static final Long PKTTMP_CODEC_ID = 1L;
    public static final Long STATE_CHANGED_CODEC_ID = 2L;
    public static final Long FLOW_NOTIFICATION_CODEC_ID = 3L;
    public static final Long EVENT_MOD_CODEC_ID = 4L;

    private BebaConstants() {}

}
