class DummyClass_91590 {
    @Test
    public void testHiveConf() {
        Properties properties = SourceConfigurationUtil.loadHiveJDBCProperties();
        assertTrue(properties.containsKey("hiveconf:hive.auto.convert.join.noconditionaltask.size"));
    }

}