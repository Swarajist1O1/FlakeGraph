class DummyClass_70830 {
    @Test(expected = ConfigException.class)
    public void testAdminListenersNotAllowingEmptyStrings() {
        Map<String, String> props = baseProps();
        props.put(WorkerConfig.ADMIN_LISTENERS_CONFIG, "http://a.b:9999,");
        new WorkerConfig(WorkerConfig.baseConfigDef(), props);
    }

}