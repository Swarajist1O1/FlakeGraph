class DummyClass_70804 {
    @Test
    public void testOrderOfPluginUrlsWithJars() throws Exception {
        createBasicDirectoryLayout();
        // Here this method is just used to create the files. The result is not used.
        createBasicExpectedUrls();

        List<Path> actual = PluginUtils.pluginUrls(pluginPath);
        // 'simple-transform.jar' is created first. In many cases, without sorting within the
        // PluginUtils, this jar will be placed before 'another-transform.jar'. However this is
        // not guaranteed because a DirectoryStream does not maintain a certain order in its
        // results. Besides this test case, sorted order in every call to assertUrls below.
        int i = Arrays.toString(actual.toArray()).indexOf("another-transform.jar");
        int j = Arrays.toString(actual.toArray()).indexOf("simple-transform.jar");
        assertTrue(i < j);
    }

}