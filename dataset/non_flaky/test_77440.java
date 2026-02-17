class DummyClass_77440 {
    @Test
    public void givenProjectWithDependencyButNoLicenseFileThenShouldReturnException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Missing LICENSE for "));

        project.getDependencies().add("compile", project.getDependencies().localGroovy());

        getLicensesDir(project).mkdir();
        updateShas.updateShas();
        task.get().checkDependencies();
    }

}