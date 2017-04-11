/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.codec.message;

public class BebaMessageCodecs {

    public static final EventModCodec EVENT_MOD_CODEC = new EventModCodec();
    public static final PkttmpModCodec PKTTMP_MOD_CODEC = new PkttmpModCodec();
    public static final StateModCodec STATE_MOD_CODEC = new StateModCodec();
    public static final StateChangedCodec STATE_CHANGED_CODEC = new StateChangedCodec();
    public static final FlowNotifCodec FLOW_NOTIF_CODEC = new FlowNotifCodec();
}
