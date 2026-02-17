class DummyClass_91557 {
    @Test
    public void testCopy() throws IOException {
        KylinConfig dstConfig = KylinConfig.createInstanceFromUri(dstPath);
        ResourceStore srcStore = ResourceStore.getStore(KylinConfig.getInstanceFromEnv());
        ResourceStore dstStore = ResourceStore.getStore(dstConfig);

        //metadata under source path and destination path are not equal before copy
        Assert.assertNotEquals(srcStore.listResources("/"), dstStore.listResources("/"));

        new ResourceTool().copy(KylinConfig.getInstanceFromEnv(), dstConfig, "/");

        //After copy, two paths have same metadata
        NavigableSet<String> dstFiles = dstStore.listResourcesRecursively("/");
        NavigableSet<String> srcFiles = srcStore.listResourcesRecursively("/");
        Assert.assertTrue(srcFiles.containsAll(EXEC_FILES));
        Assert.assertFalse(dstFiles.containsAll(EXEC_FILES));
        srcFiles.removeAll(EXEC_FILES);
        Assert.assertEquals(srcFiles, dstFiles);
    }

}