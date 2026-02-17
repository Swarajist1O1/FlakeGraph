class DummyClass_77446 {
    @Test
    public void givenProjectWithANoticeButWithoutTheDependencyThenShouldThrowException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Unused notice "));

        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);
        createAllDefaultDependencyFiles(licensesDir, "groovy-all");
        createFileIn(licensesDir, "non-declared-NOTICE.txt", "");

        task.get().checkDependencies();
    }

}