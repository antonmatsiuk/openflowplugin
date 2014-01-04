package org.opendaylight.openflowplugin.openflow.md.core.sal.convertor.flowflag;

import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.opendaylight.openflowplugin.openflow.md.OFConstants;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowModFlags;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.FlowModInputBuilder;

/**
 * match conversion and injection test 
 */
public class FlowFlagReactorTest {

    private FlowModFlags[] flowFlags;
    
    /**
     * prepare input match
     */
    @Before
    public void setUp() {
        flowFlags = new FlowModFlags[] {
                new FlowModFlags(true, true, true, true, true),
                new FlowModFlags(false, false, false, false, false),
                new FlowModFlags(true, false, true, false, true)
        };
    }

    /**
     * convert for OF-1.3, inject into {@link FlowModInputBuilder}
     */
    @Test
    public void testMatchConvertorV13_flow() {
        FlowModInputBuilder target = new FlowModInputBuilder();
        for (FlowModFlags fFlag : flowFlags) {
            target.setFlags(null);
            FlowFlagReactor.getInstance().convert(fFlag, 
                    OFConstants.OFP_VERSION_1_3, target,BigInteger.valueOf(1));
            Assert.assertNotNull(target.getFlags());
        }
    }
    
    /**
     * convert for OF-1.0, inject into {@link FlowModInputBuilder}
     */
    @Test
    public void testMatchConvertorV10_flow() {
        FlowModInputBuilder target = new FlowModInputBuilder();
        for (FlowModFlags fFlag : flowFlags) {
            target.setFlagsV10(null);
            FlowFlagReactor.getInstance().convert(fFlag, 
                    OFConstants.OFP_VERSION_1_0, target,BigInteger.valueOf(1));
            Assert.assertNotNull(target.getFlagsV10());
        }
    }
}