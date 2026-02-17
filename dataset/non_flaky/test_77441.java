class DummyClass_77441 {
    @Test
    public void givenProjectWithDependencyButNoNoticeFileThenShouldReturnException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Missing NOTICE for "));

        project.getDependencies().add("compile", dependency);

        createFileIn(getLicensesDir(project), "groovy-all-LICENSE.txt", PERMISSIVE_LICENSE_TEXT);

        updateShas.updateShas();
        task.get().checkDependencies();
    }

}