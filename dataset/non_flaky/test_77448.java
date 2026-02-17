class DummyClass_77448 {
    @Test
    public void givenProjectWithADependencyWithWrongShaThenShouldThrowException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("SHA has changed! Expected "));

        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);
        createAllDefaultDependencyFiles(licensesDir, "groovy-all");

        Path groovySha = Files.list(licensesDir.toPath()).filter(file -> file.toFile().getName().contains("sha")).findFirst().get();

        Files.write(groovySha, new byte[] { 1 }, StandardOpenOption.CREATE);

        task.get().checkDependencies();
    }

}