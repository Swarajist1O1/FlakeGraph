class DummyClass_162376 {
    @Test
    public void testStopped() throws Exception {
        assertFalse(DockerStatus.isContainerStopped(running));
        assertFalse(DockerStatus.isContainerStopped(runningVariant));
        assertFalse(DockerStatus.isContainerStopped(shortRunning));
        assertFalse(DockerStatus.isContainerStopped(created));
        assertFalse(DockerStatus.isContainerStopped(createdVariant));
        assertTrue(DockerStatus.isContainerStopped(exited));
        assertFalse(DockerStatus.isContainerStopped(paused));
    }

}