class DummyClass_91560 {
    @Test
    public void testFileStore() throws Exception {
        KylinConfig config = KylinConfig.getInstanceFromEnv();
        ResourceStoreTest.testAStore(config.getMetadataUrl().toString(), config);
    }

}