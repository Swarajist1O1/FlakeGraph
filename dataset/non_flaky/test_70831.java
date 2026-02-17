class DummyClass_70831 {
    @Test(expected = ConfigException.class)
    public void testAdminListenersNotAllowingBlankStrings() {
        Map<String, String> props = baseProps();
        props.put(WorkerConfig.ADMIN_LISTENERS_CONFIG, "http://a.b:9999, ,https://a.b:9999");
        new WorkerConfig(WorkerConfig.baseConfigDef(), props);
    }

}