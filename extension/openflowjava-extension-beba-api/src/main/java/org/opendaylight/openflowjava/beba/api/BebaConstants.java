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


    //enum ofp_state_mod_command
    public static final Long OFPSC_EXP_STATEFUL_TABLE_CONFIG = 0L;
    public static final Long OFPSC_EXP_SET_L_EXTRACTOR = 1L;
    public static final Long OFPSC_EXP_SET_U_EXTRACTOR = 2L;
    public static final Long OFPSC_EXP_SET_FLOW_STATE = 3L;
    public static final Long OFPSC_EXP_DEL_FLOW_STATE = 4L;
    public static final Long OFPSC_EXP_SET_GLOBAL_STATE = 5L;
    public static final Long OFPSC_EXP_RESET_GLOBAL_STATE = 6L;


    //enum ofp_exp_actions
    public static final Long OFPAT_EXP_SET_STATE = 0L;
    public static final Long OFPAT_EXP_SET_GLOBAL_STATE = 1L;
    public static final Long OFPAT_EXP_INC_STATE = 2L;


    //enum ofp_pkttmp_mod_command
    public static final Long OFPSC_ADD_PKTTMP = 0L;
    public static final Long OFPSC_DEL_PKTTMP = 1L;


    //enum ofp_exp_msg_event_mod_commands
    public static final Long OFPSC_ADD_EVENT = 0L;
    public static final Long OFPSC_DEL_EVENT = 1L;


    //enum ofp_event_type
    public static final Long OFPE_PORT_STATE = 0L;
    public static final Long OFPE_FLOW_EXPIRED = 1L;
    public static final Long OFPE_TIMER_EXPIRED = 2L;
    public static final Long OFPE_BUCKET_STATE = 3L;

    //enum ofp_event_reaction_type
    public static final Long OFPR_EXP_INSTRUCTION = 0L;

    //enum ofp_exp_instructions
    public static final Long OFPIT_IN_SWITCH_PKT_GEN = 0L;
    public static final Long OFPIT_PORT_MOD = 1L;


    //enum ofp_stats_extension_commands
    public static final Long OFPMP_EXP_STATE_STATS = 0L;
    public static final Long OFPMP_EXP_GLOBAL_STATE_STATS = 1L;
    public static final Long OFPMP_EXP_STATE_STATS_AND_DELETE = 2L;


    //enum ofp_error_type
    public static final Long OFPET_EXPERIMENTER = (long) 0xffff;


    //enum ofp_experimenter_code
    public static final Long OFPEC_EXP_STATE_MOD_FAILED = 0L;
    public static final Long OFPEC_EXP_STATE_MOD_BAD_COMMAND  = 1L;
    public static final Long OFPEC_EXP_SET_EXTRACTOR = 2L;
    public static final Long OFPEC_EXP_SET_FLOW_STATE = 3L;
    public static final Long OFPEC_EXP_DEL_FLOW_STATE = 4L;
    public static final Long OFPEC_BAD_EXP_MESSAGE = 5L;
    public static final Long OFPEC_BAD_EXP_ACTION = 6L;
    public static final Long OFPEC_BAD_EXP_LEN = 7L;
    public static final Long OFPEC_BAD_TABLE_ID = 8L;
    public static final Long OFPEC_BAD_MATCH_WILDCARD = 9L;
    public static final Long OFPEC_BAD_EXP_INSTRUCTION = 10L;
    public static final Long OFPEC_EXP_PKTTMP_MOD_FAILED = 11L;
    public static final Long OFPEC_EXP_PKTTMP_MOD_BAD_COMMAND = 12L;
    public static final Long OFPEC_EXP_EVENT_MOD_FAILED = 13L;
    public static final Long OFPEC_EXP_EVENT_MOD_BAD_COMMAND = 14L;


    private BebaConstants() {}

}
