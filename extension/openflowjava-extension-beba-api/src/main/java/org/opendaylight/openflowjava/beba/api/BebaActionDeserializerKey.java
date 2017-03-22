/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.beba.api;

public final class BebaActionDeserializerKey {

    private final short version;
    private final int subtype;

    /**
     * @param version protocol wire version
     * @param subtype beba_action_subtype
     */
    public BebaActionDeserializerKey(short version, int subtype) {
        if (!isValueUint32(subtype)) {
            throw new IllegalArgumentException(
                    "Beba exp_type is uint32. A value of subtype has to be between 0 and 4294967295 include.");
        }
        this.version = version;
        this.subtype = subtype;
    }

    public short getVersion() {
        return version;
    }

    public int getSubtype() {
        return subtype;
    }

    private static final boolean isValueUint32(int value) {
        if (value >= 0 && value <= 4294967295L)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + subtype;
        result = prime * result + version;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BebaActionDeserializerKey other = (BebaActionDeserializerKey) obj;
        if (subtype != other.subtype)
            return false;
        if (version != other.version)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BebaActionDeserializerKey [version=" + version + ", subtype=" + subtype + "]";
    }

}
