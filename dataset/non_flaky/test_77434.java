class DummyClass_77434 {
    @Test
    public void whenDependencyAndWrongShaExistsThenShouldNotOverwriteShaFile() throws IOException, NoSuchAlgorithmException {
        project.getDependencies().add("compile", dependency);

        File groovyJar = task.getParentTask().getDependencies().getFiles().iterator().next();
        String groovyShaName = groovyJar.getName() + ".sha1";

        File groovySha = createFileIn(getLicensesDir(project), groovyShaName, "content");
        task.updateShas();

        assertThat(FileUtils.readFileToString(groovySha), equalTo("content"));
    }

}