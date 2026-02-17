class DummyClass_76763 {
    @Test
    public void testUberJarMavenPluginConfiguration()
            throws MavenInvocationException, IOException, InterruptedException {
        testDir = initProject("projects/uberjar-maven-plugin-config");
        running = new RunningInvoker(testDir, false);
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap());
        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        verifyUberJar();
    }

}