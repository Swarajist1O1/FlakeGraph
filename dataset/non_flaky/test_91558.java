class DummyClass_91558 {
    @Test
    public void testListResourcesImpl() throws Exception {
        String path = "../examples/test_metadata/";
        String cp = new File(path).getCanonicalFile().getPath();
        FileSystem fs = HadoopUtil.getFileSystem(cp);
        HDFSResourceStore store = new HDFSResourceStore(KylinConfig.getInstanceFromEnv(),
                StorageURL.valueOf("hdfs@hdfs"));
        Field field = store.getClass().getDeclaredField("fs");
        field.setAccessible(true);
        field.set(store, fs);

        File f1 = new File(cp + "/resource/resource/e1.json");
        File f2 = new File(cp + "/resource/resource/e2.json");
        if (!f1.getParentFile().exists()) {
            if (!f1.getParentFile().mkdirs()) {
                throw new RuntimeException("Can not create dir.");
            }
        }
        if (!(f1.createNewFile() && f2.createNewFile())) {
            throw new RuntimeException("Can not create file.");
        }

        Path p = new Path(cp);
        TreeSet<String> resources = store.getAllFilePath(new Path(p, "resource"), "/resource/");
        TreeSet<String> expected = new TreeSet<>();
        expected.add("/resource/resource/e1.json");
        expected.add("/resource/resource/e2.json");
        Assert.assertEquals(expected, resources);
    }

}