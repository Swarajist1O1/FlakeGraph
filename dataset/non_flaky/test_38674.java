class DummyClass_38674 {
    @Test(expectedExceptions = NullPointerException.class,
    public void testRequiredConfigMissing() throws Exception {
        Map<String, Object> map = buildValidConfigMap();
        map.remove("influxdbUrl");
        InfluxDBSinkConfig config = InfluxDBSinkConfig.load(map);
        config.validate();
    }

}