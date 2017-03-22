/*
 * Copyright (c) 2017 NEC Corporation.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowjava.beba.api;

public class BebaMessageDeserializerKey {


      private final short version;
      private final long exptype;

        /**
         * @param version protocol wire version
         * @param subtype beba_action_subtype
         */
        public BebaMessageDeserializerKey(short version, long exptype) {
            if (!isValueUint32(exptype)) {
                throw new IllegalArgumentException(
                        "Beba exp_type is uint32. A value of subtype has to be between 0 and 4294967295 include.");
            }
            this.version = version;
            this.exptype = exptype;
        }

        public short getVersion() {
            return version;
        }

        public long getExptype() {
            return exptype;
        }

        private static final boolean isValueUint32(long value) {
            if (value >= 0 && value <= 4294967295L) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + version;
            result = prime * result + hashCodeOfLong(exptype);
            return result;
        }

        protected int hashCodeOfLong(long longValue) {
            return (int) (longValue ^ longValue >>> 32);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            BebaMessageDeserializerKey other = (BebaMessageDeserializerKey) obj;
            if (exptype != other.exptype) {
                return false;
            }
            if (version != other.version) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "BebaMessageDeserializerKey [version=" + version + ", subtype=" + exptype + "]";
        }
}
