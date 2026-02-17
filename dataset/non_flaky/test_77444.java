class DummyClass_77444 {
    @Test
    public void givenProjectWithDependencyAndEverythingInOrderThenShouldReturnSilently() throws Exception {
        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);

        createAllDefaultDependencyFiles(licensesDir, "groovy-all");
        task.get().checkDependencies();
    }

}