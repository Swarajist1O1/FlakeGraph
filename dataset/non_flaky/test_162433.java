class DummyClass_162433 {
    @Test
    public void testExecInContainer() throws Exception {

        // The older "lxc" execution driver doesn't support "exec". At the time of writing (2016/03/29),
        // that's the case for CircleCI.
        // Once they resolve the issue, this clause can be removed.
        Assume.assumeTrue(TestEnvironment.dockerExecutionDriverSupportsExec());

        final GenericContainer.ExecResult result = redis.execInContainer("redis-cli", "role");
        assertTrue("Output for \"redis-cli role\" command should start with \"master\"", result.getStdout().startsWith("master"));
        assertEquals("Stderr for \"redis-cli role\" command should be empty", "", result.getStderr());
        // We expect to reach this point for modern Docker versions.
    }

}