class DummyClass_33914 {
    @Test
    public void testPersistentTS() throws Exception {
        runTest("direct:create-pts", GridFSBuckets.create(mongo.getDatabase("test"), getBucket() + "-pts"));
    }

}