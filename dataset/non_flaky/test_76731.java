class DummyClass_76731 {
    @Test
    public void testThatMutableFastJarWorksProvidersDirOutsideOutputDir() throws Exception {
        assertThatMutableFastJarWorks("outsidedir", ".." + File.separator + "providers");
    }

}