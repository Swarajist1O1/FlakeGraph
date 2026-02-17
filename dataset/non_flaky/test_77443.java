class DummyClass_77443 {
    @Test
    public void givenProjectWithStrictDependencyAndEverythingInOrderThenShouldReturnSilently() throws Exception {
        project.getDependencies().add("compile", dependency);

        createFileIn(getLicensesDir(project), "groovy-all-LICENSE.txt", STRICT_LICENSE_TEXT);
        createFileIn(getLicensesDir(project), "groovy-all-NOTICE.txt", "");
        createFileIn(getLicensesDir(project), "groovy-all-SOURCES.txt", "");

        updateShas.updateShas();
        task.get().checkDependencies();
    }

}