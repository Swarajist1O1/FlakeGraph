class DummyClass_77437 {
    @Test
    public void givenProjectWithoutLicensesDirButWithDependenciesThenShouldThrowException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("does not exist, but there are dependencies"));

        project.getDependencies().add("compile", dependency);
        task.get().checkDependencies();
    }

}