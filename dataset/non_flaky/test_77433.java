class DummyClass_77433 {
    @Test
    public void whenDependencyExistsButShaNotThenShouldCreateNewShaFile() throws IOException, NoSuchAlgorithmException {
        project.getDependencies().add("compile", dependency);

        getLicensesDir(project).mkdir();
        task.updateShas();

        Path groovySha = Files.list(getLicensesDir(project).toPath()).findFirst().get();

        assertTrue(groovySha.toFile().getName().startsWith("groovy-all"));
    }

}