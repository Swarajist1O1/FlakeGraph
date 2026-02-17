class DummyClass_70794 {
    @Test
    public void testLoadingPluginClass() throws ClassNotFoundException {
        TestPlugins.assertAvailable();
        DelegatingClassLoader classLoader = new DelegatingClassLoader(TestPlugins.pluginPath());
        classLoader.initLoaders();
        for (String pluginClassName : TestPlugins.pluginClasses()) {
            assertNotNull(classLoader.loadClass(pluginClassName));
            assertNotNull(classLoader.pluginClassLoader(pluginClassName));
        }
    }

}