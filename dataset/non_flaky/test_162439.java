class DummyClass_162439 {
    @Test
    public void addExposedPortAfterWithExposedPortsTest() {
        redis.addExposedPort(8987);
        assertThat("Both ports should be exposed", redis.getExposedPorts().size(), equalTo(2));
        assertTrue("withExposedPort should be exposed", redis.getExposedPorts().contains(REDIS_PORT));
        assertTrue("addExposedPort should be exposed", redis.getExposedPorts().contains(8987));
    }

}