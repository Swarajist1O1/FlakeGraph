class DummyClass_70826 {
    @Test
    public void testPluginDescWithNullVersion() {
        String nullVersion = "null";
        PluginDesc<SourceConnector> connectorDesc = new PluginDesc<>(
                SourceConnector.class,
                null,
                pluginLoader
        );

        assertPluginDesc(
                connectorDesc,
                SourceConnector.class,
                nullVersion,
                pluginLoader.location()
        );

        String location = "classpath";
        PluginDesc<Converter> converterDesc = new PluginDesc<>(
                Converter.class,
                null,
                systemLoader
        );

        assertPluginDesc(converterDesc, Converter.class, nullVersion, location);
    }

}