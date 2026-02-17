class DummyClass_77436 {
    @Test
    public void givenProjectWithLicensesDirButNoDependenciesThenShouldThrowException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("exists, but there are no dependencies"));

        getLicensesDir(project).mkdir();
        task.get().checkDependencies();
    }

}