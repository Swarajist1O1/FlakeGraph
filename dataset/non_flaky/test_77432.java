class DummyClass_77432 {
    @Test
    public void whenDependencyDoesntExistThenShouldDeleteDependencySha() throws IOException, NoSuchAlgorithmException {

        File unusedSha = createFileIn(getLicensesDir(project), "test.sha1", "");
        task.updateShas();

        assertFalse(unusedSha.exists());
    }

}