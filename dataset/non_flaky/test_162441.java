class DummyClass_162441 {
    @Test
    public void simpleTest() throws Exception {
        final String release = container.execInContainer("cat", "/etc/alpine-release").getStdout();

        assertTrue("/etc/alpine-release starts with " + expectedVersion,
                release.startsWith(expectedVersion));
    }

}