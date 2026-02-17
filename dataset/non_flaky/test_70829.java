class DummyClass_70829 {
    @Test
    public void testAdminListenersConfigAllowedValues() {
        Map<String, String> props = baseProps();

        // no value set for "admin.listeners"
        WorkerConfig config = new WorkerConfig(WorkerConfig.baseConfigDef(), props);
        assertNull("Default value should be null.", config.getList(WorkerConfig.ADMIN_LISTENERS_CONFIG));

        props.put(WorkerConfig.ADMIN_LISTENERS_CONFIG, "");
        config = new WorkerConfig(WorkerConfig.baseConfigDef(), props);
        assertTrue(config.getList(WorkerConfig.ADMIN_LISTENERS_CONFIG).isEmpty());

        props.put(WorkerConfig.ADMIN_LISTENERS_CONFIG, "http://a.b:9999, https://a.b:7812");
        config = new WorkerConfig(WorkerConfig.baseConfigDef(), props);
        assertEquals(config.getList(WorkerConfig.ADMIN_LISTENERS_CONFIG), Arrays.asList("http://a.b:9999", "https://a.b:7812"));

        new WorkerConfig(WorkerConfig.baseConfigDef(), props);
    }

}