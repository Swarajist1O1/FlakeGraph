class DummyClass_162403 {
    @Test
    public void testGetServicePort() {
        int serviceWithInstancePort = environment.getServicePort("redis_1", REDIS_PORT);
        assertNotNull("Port is set for service with instance number", serviceWithInstancePort);
        int serviceWithoutInstancePort = environment.getServicePort("redis", REDIS_PORT);
        assertNotNull("Port is set for service with instance number", serviceWithoutInstancePort);
        assertEquals("Service ports are the same", serviceWithInstancePort, serviceWithoutInstancePort);
    }

}