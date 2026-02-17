class DummyClass_77439 {
    @Test
    public void givenProjectWithDependencyButNoShaFileThenShouldReturnException() throws Exception {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("Missing SHA for "));

        File licensesDir = getLicensesDir(project);
        createFileIn(licensesDir, "groovy-all-LICENSE.txt", PERMISSIVE_LICENSE_TEXT);
        createFileIn(licensesDir, "groovy-all-NOTICE.txt", "");

        project.getDependencies().add("compile", project.getDependencies().localGroovy());
        task.get().checkDependencies();
    }

}