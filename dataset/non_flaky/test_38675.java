class DummyClass_38675 {
    @Test(expectedExceptions = IllegalArgumentException.class,
    public void testBatchConfig() throws Exception {
        Map<String, Object> map = buildValidConfigMap();
        map.put("batchSize", -1);
        InfluxDBSinkConfig config = InfluxDBSinkConfig.load(map);
        config.validate();
    }

}