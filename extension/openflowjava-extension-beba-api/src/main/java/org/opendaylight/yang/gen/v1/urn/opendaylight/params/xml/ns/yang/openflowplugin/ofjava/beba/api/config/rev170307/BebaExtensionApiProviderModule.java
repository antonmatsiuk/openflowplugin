package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.ofjava.beba.api.config.rev170307;

import org.opendaylight.openflowjava.beba.api.impl.BebaExtensionCodecRegistratorImpl;

public class BebaExtensionApiProviderModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.ofjava.beba.api.config.rev170307.AbstractBebaExtensionApiProviderModule {
    public BebaExtensionApiProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public BebaExtensionApiProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.ofjava.beba.api.config.rev170307.BebaExtensionApiProviderModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        return new BebaExtensionCodecRegistratorImpl(getOpenflowSwitchConnectionProviderDependency());
    }

}
