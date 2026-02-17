class DummyClass_70827 {
    @Test
    public void testPluginDescEquality() {
        PluginDesc<Connector> connectorDescPluginPath = new PluginDesc<>(
                Connector.class,
                snaphotVersion,
                pluginLoader
        );

        PluginDesc<Connector> connectorDescClasspath = new PluginDesc<>(
                Connector.class,
                snaphotVersion,
                systemLoader
        );

        assertEquals(connectorDescPluginPath, connectorDescClasspath);
        assertEquals(connectorDescPluginPath.hashCode(), connectorDescClasspath.hashCode());

        PluginDesc<Converter> converterDescPluginPath = new PluginDesc<>(
                Converter.class,
                noVersion,
                pluginLoader
        );

        PluginDesc<Converter> converterDescClasspath = new PluginDesc<>(
                Converter.class,
                noVersion,
                systemLoader
        );

        assertEquals(converterDescPluginPath, converterDescClasspath);
        assertEquals(converterDescPluginPath.hashCode(), converterDescClasspath.hashCode());

        PluginDesc<Transformation> transformDescPluginPath = new PluginDesc<>(
                Transformation.class,
                null,
                pluginLoader
        );

        PluginDesc<Transformation> transformDescClasspath = new PluginDesc<>(
                Transformation.class,
                noVersion,
                pluginLoader
        );

        assertNotEquals(transformDescPluginPath, transformDescClasspath);
    }

}