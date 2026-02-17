class DummyClass_162401 {
    @Test
    public void secondTest() {
        // used in manual checking for cleanup in between tests
        Jedis jedis = new Jedis(getEnvironment().getServiceHost("redis_1", REDIS_PORT), getEnvironment().getServicePort("redis_1", REDIS_PORT));

        jedis.incr("test");
        jedis.incr("test");
        jedis.incr("test");

        assertEquals("Tests use fresh container instances", "3", jedis.get("test"));
        // if these end up using the same container one of the test methods will fail.
        // However, @Rule creates a separate DockerComposeContainer instance per test, so this just shouldn't happen
    }

}