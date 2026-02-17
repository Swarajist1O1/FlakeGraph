class DummyClass_77435 {
    @Test
    public void whenLicensesDirDoesntExistThenShouldThrowException() throws IOException, NoSuchAlgorithmException {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("isn't a valid directory"));

        task.updateShas();
    }

}