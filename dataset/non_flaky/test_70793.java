class DummyClass_70793 {
    @Test(expected = ClassNotFoundException.class)
    public void testLoadingUnloadedPluginClass() throws ClassNotFoundException {
        TestPlugins.assertAvailable();
        DelegatingClassLoader classLoader = new DelegatingClassLoader(Collections.emptyList());
        classLoader.initLoaders();
        for (String pluginClassName : TestPlugins.pluginClasses()) {
            classLoader.loadClass(pluginClassName);
        }
    }

}