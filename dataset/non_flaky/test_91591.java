class DummyClass_91591 {
    @Test
    public void testSqoopConf() {
        Map<String, String> configMap = SourceConfigurationUtil.loadSqoopConfiguration();
        assertFalse(configMap.isEmpty());
        assertEquals("1", configMap.get("dfs.replication"));
    }

}