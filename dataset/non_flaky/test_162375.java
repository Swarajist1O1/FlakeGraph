class DummyClass_162375 {
    @Test
    public void testRunning() throws Exception {
        assertTrue(DockerStatus.isContainerRunning(running, minimumDuration, now));
        assertTrue(DockerStatus.isContainerRunning(runningVariant, minimumDuration, now));
        assertFalse(DockerStatus.isContainerRunning(shortRunning, minimumDuration, now));
        assertFalse(DockerStatus.isContainerRunning(created, minimumDuration, now));
        assertFalse(DockerStatus.isContainerRunning(createdVariant, minimumDuration, now));
        assertFalse(DockerStatus.isContainerRunning(exited, minimumDuration, now));
        assertFalse(DockerStatus.isContainerRunning(paused, minimumDuration, now));
    }

}