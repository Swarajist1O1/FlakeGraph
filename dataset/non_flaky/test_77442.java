class DummyClass_77442 {
    @Test
    public void givenProjectWithStrictDependencyButNoSourcesFileThenShouldReturnException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Missing SOURCES for "));

        project.getDependencies().add("compile", dependency);

        createFileIn(getLicensesDir(project), "groovy-all-LICENSE.txt", STRICT_LICENSE_TEXT);
        createFileIn(getLicensesDir(project), "groovy-all-NOTICE.txt", "");

        updateShas.updateShas();
        task.get().checkDependencies();
    }

}