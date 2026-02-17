class DummyClass_70813 {
    @Test
    public void shouldInstantiateAndConfigureConnectRestExtension() {
        props.put(WorkerConfig.REST_EXTENSION_CLASSES_CONFIG,
                  TestConnectRestExtension.class.getName());
        createConfig();

        List<ConnectRestExtension> connectRestExtensions =
            plugins.newPlugins(config.getList(WorkerConfig.REST_EXTENSION_CLASSES_CONFIG),
                               config,
                               ConnectRestExtension.class);
        assertNotNull(connectRestExtensions);
        assertEquals("One Rest Extension expected", 1, connectRestExtensions.size());
        assertNotNull(connectRestExtensions.get(0));
        assertTrue("Should be instance of TestConnectRestExtension",
                   connectRestExtensions.get(0) instanceof TestConnectRestExtension);
        assertNotNull(((TestConnectRestExtension) connectRestExtensions.get(0)).configs);
        assertEquals(config.originals(),
                     ((TestConnectRestExtension) connectRestExtensions.get(0)).configs);
    }

}