class DummyClass_77450 {
    @Test
    public void givenProjectWithAIgnoreShaConfigurationAndNoShaFileThenShouldReturnSilently() throws Exception {
        project.getDependencies().add("compile", dependency);

        File licensesDir = getLicensesDir(project);
        createFileIn(licensesDir, "groovy-all-LICENSE.txt", PERMISSIVE_LICENSE_TEXT);
        createFileIn(licensesDir, "groovy-all-NOTICE.txt", "");

        task.get().ignoreSha("groovy-all");
        task.get().checkDependencies();
    }

}