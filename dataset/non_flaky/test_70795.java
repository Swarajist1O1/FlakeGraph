class DummyClass_70795 {
    @Test
    public void testJavaLibraryClasses() {
        assertFalse(PluginUtils.shouldLoadInIsolation("java."));
        assertFalse(PluginUtils.shouldLoadInIsolation("java.lang.Object"));
        assertFalse(PluginUtils.shouldLoadInIsolation("java.lang.String"));
        assertFalse(PluginUtils.shouldLoadInIsolation("java.util.HashMap$Entry"));
        assertFalse(PluginUtils.shouldLoadInIsolation("java.io.Serializable"));
        assertFalse(PluginUtils.shouldLoadInIsolation("javax.rmi."));
        assertFalse(PluginUtils.shouldLoadInIsolation(
                "javax.management.loading.ClassLoaderRepository")
        );
        assertFalse(PluginUtils.shouldLoadInIsolation("org.omg.CORBA."));
        assertFalse(PluginUtils.shouldLoadInIsolation("org.omg.CORBA.Object"));
        assertFalse(PluginUtils.shouldLoadInIsolation("org.w3c.dom."));
        assertFalse(PluginUtils.shouldLoadInIsolation("org.w3c.dom.traversal.TreeWalker"));
        assertFalse(PluginUtils.shouldLoadInIsolation("org.xml.sax."));
        assertFalse(PluginUtils.shouldLoadInIsolation("org.xml.sax.EntityResolver"));
    }

}