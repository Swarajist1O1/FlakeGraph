class DummyClass_76766 {
    @Test
    public void testPackageWorksWhenUberjarIsTrue()
            throws MavenInvocationException, IOException, InterruptedException {
        testDir = initProject("projects/uberjar-check");

        createAndVerifyUberJar();
        // ensure that subsequent package without clean also works
        createAndVerifyUberJar();
    }

}