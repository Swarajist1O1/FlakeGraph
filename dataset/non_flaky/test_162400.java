class DummyClass_162400 {
    @Test
    public void simpleTest() {
        Jedis jedis = new Jedis(getEnvironment().getServiceHost("redis_1", REDIS_PORT), getEnvironment().getServicePort("redis_1", REDIS_PORT));

        jedis.incr("test");
        jedis.incr("test");
        jedis.incr("test");

        assertEquals("A redis instance defined in compose can be used in isolation", "3", jedis.get("test"));
    }

}