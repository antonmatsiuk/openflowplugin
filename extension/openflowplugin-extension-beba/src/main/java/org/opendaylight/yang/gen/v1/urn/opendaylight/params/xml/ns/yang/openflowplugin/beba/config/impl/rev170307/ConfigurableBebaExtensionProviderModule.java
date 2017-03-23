package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.beba.config.impl.rev170307;

import org.opendaylight.openflowplugin.extension.api.ExtensionConverterRegistrator;
import org.opendaylight.openflowplugin.extension.vendor.beba.BebaExtensionProvider;

public class ConfigurableBebaExtensionProviderModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.beba.config.impl.rev170307.AbstractConfigurableBebaExtensionProviderModule {
    public ConfigurableBebaExtensionProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public ConfigurableBebaExtensionProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.openflowplugin.beba.config.impl.rev170307.ConfigurableBebaExtensionProviderModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        BebaExtensionProvider provider = new BebaExtensionProvider();
        ExtensionConverterRegistrator registrator = getOpenflowPluginExtensionRegistryDependency().getExtensionConverterRegistrator();
        provider.setExtensionConverterRegistrator(registrator);
        provider.registerConverters();
        return provider;
    }

}
