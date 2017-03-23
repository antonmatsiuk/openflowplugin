package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.ofjava.beba.config.rev170307;

import org.opendaylight.openflowjava.beba.BebaExtensionsRegistrator;

public class BebaExtensionProviderModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.ofjava.beba.config.rev170307.AbstractBebaExtensionProviderModule {
    public BebaExtensionProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public BebaExtensionProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.ofjava.beba.config.rev170307.BebaExtensionProviderModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        BebaExtensionsRegistrator registrator = new BebaExtensionsRegistrator(getBebaExtensionCodecRegistratorDependency());
        registrator.registerBebaExtensions();
        return registrator;
    }

}
