class DummyClass_162409 {
    @Test
    public void testFixedHostPortMapping() throws IOException {
        // first find a free port on the docker host that will work for testing
        GenericContainer portDiscoveryRedis = new GenericContainer("redis:3.0.2").withExposedPorts(REDIS_PORT);
        portDiscoveryRedis.start();
        Integer freePort = portDiscoveryRedis.getMappedPort(REDIS_PORT);
        portDiscoveryRedis.stop();


        // Set up a FixedHostPortGenericContainer as if this were a @Rule
        FixedHostPortGenericContainer redis = new FixedHostPortGenericContainer("redis:3.0.2").withFixedExposedPort(freePort, REDIS_PORT);
        redis.start();

//        Config redisConfig = new Config();
//        redisConfig.useSingleServer().setAddress(redis.getContainerIpAddress() + ":" + freePort);
//        Redisson redisson = Redisson.create(redisConfig);
//
//        redisson.getBucket("test").set("foo");
//
//        assertEquals("The bucket content was successfully set", "foo", redisson.getBucket("test").get());
//        assertEquals("The container returns the fixed port from getMappedPort(...)", freePort, redis.getMappedPort(REDIS_PORT));
    }

}