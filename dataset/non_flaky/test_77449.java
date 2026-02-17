class DummyClass_77449 {
    @Test
    public void givenProjectWithADependencyMappingThenShouldReturnSilently() throws Exception {
        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);
        createAllDefaultDependencyFiles(licensesDir, "groovy");

        Map<String, String> mappings = new HashMap<>();
        mappings.put("from", "groovy-all");
        mappings.put("to", "groovy");

        task.get().mapping(mappings);
        task.get().checkDependencies();
    }

}