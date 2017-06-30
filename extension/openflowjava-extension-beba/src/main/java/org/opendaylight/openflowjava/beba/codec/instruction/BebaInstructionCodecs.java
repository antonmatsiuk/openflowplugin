/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.instruction;

public class BebaInstructionCodecs {

    public static final InSpCodec OFPIT_IN_SWITCH_PKT_GEN_CODEC = new InSpCodec();
    public static final PortModCodec OFPIT_PKTTMP_MOD_CODEC = new PortModCodec();

}
