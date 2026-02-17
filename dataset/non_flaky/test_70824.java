class DummyClass_70824 {
    @Test
    public void testRegularPluginDesc() {
        PluginDesc<Connector> connectorDesc = new PluginDesc<>(
                Connector.class,
                regularVersion,
                pluginLoader
        );

        assertPluginDesc(connectorDesc, Connector.class, regularVersion, pluginLoader.location());

        PluginDesc<Converter> converterDesc = new PluginDesc<>(
                Converter.class,
                snaphotVersion,
                pluginLoader
        );

        assertPluginDesc(converterDesc, Converter.class, snaphotVersion, pluginLoader.location());

        PluginDesc<Transformation> transformDesc = new PluginDesc<>(
                Transformation.class,
                noVersion,
                pluginLoader
        );

        assertPluginDesc(transformDesc, Transformation.class, noVersion, pluginLoader.location());
    }

}