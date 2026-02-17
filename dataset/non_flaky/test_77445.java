class DummyClass_77445 {
    @Test
    public void givenProjectWithALicenseButWithoutTheDependencyThenShouldThrowException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Unused license "));

        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);
        createAllDefaultDependencyFiles(licensesDir, "groovy-all");
        createFileIn(licensesDir, "non-declared-LICENSE.txt", "");

        task.get().checkDependencies();
    }

}