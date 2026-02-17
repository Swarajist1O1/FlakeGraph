class DummyClass_33913 {
    @Test
    public void testAttribute() throws Exception {
        runTest("direct:create-a", GridFSBuckets.create(mongo.getDatabase("test"), getBucket() + "-a"));
    }

}