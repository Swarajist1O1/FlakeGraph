class DummyClass_77447 {
    @Test
    public void givenProjectWithAShaButWithoutTheDependencyThenShouldThrowException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Unused sha files found: \n"));

        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);
        createAllDefaultDependencyFiles(licensesDir, "groovy-all");
        createFileIn(licensesDir, "non-declared.sha1", "");

        task.get().checkDependencies();
    }

}