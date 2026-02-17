class DummyClass_70828 {
    @Test
    public void testPluginDescComparison() {
        PluginDesc<Connector> connectorDescPluginPath = new PluginDesc<>(
                Connector.class,
                regularVersion,
                pluginLoader
        );

        PluginDesc<Connector> connectorDescClasspath = new PluginDesc<>(
                Connector.class,
                newerVersion,
                systemLoader
        );

        assertNewer(connectorDescPluginPath, connectorDescClasspath);

        PluginDesc<Converter> converterDescPluginPath = new PluginDesc<>(
                Converter.class,
                noVersion,
                pluginLoader
        );

        PluginDesc<Converter> converterDescClasspath = new PluginDesc<>(
                Converter.class,
                snaphotVersion,
                systemLoader
        );

        assertNewer(converterDescPluginPath, converterDescClasspath);

        PluginDesc<Transformation> transformDescPluginPath = new PluginDesc<>(
                Transformation.class,
                null,
                pluginLoader
        );

        PluginDesc<Transformation> transformDescClasspath = new PluginDesc<>(
                Transformation.class,
                regularVersion,
                systemLoader
        );

        assertNewer(transformDescPluginPath, transformDescClasspath);
    }

}