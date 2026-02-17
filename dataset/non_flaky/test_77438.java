class DummyClass_77438 {
    @Test
    public void givenProjectWithoutLicensesDirNorDependenciesThenShouldReturnSilently() throws Exception {
        task.get().checkDependencies();
    }

}