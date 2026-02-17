class DummyClass_70825 {
    @Test
    public void testPluginDescWithSystemClassLoader() {
        String location = "classpath";
        PluginDesc<SinkConnector> connectorDesc = new PluginDesc<>(
                SinkConnector.class,
                regularVersion,
                systemLoader
        );

        assertPluginDesc(connectorDesc, SinkConnector.class, regularVersion, location);

        PluginDesc<Converter> converterDesc = new PluginDesc<>(
                Converter.class,
                snaphotVersion,
                systemLoader
        );

        assertPluginDesc(converterDesc, Converter.class, snaphotVersion, location);

        PluginDesc<Transformation> transformDesc = new PluginDesc<>(
                Transformation.class,
                noVersion,
                systemLoader
        );

        assertPluginDesc(transformDesc, Transformation.class, noVersion, location);
    }

}