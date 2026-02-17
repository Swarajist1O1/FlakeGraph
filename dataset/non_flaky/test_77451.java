class DummyClass_77451 {
    @Test
    public void givenProjectWithoutLicensesDirWhenAskingForShaFilesThenShouldThrowException() {
        expectedException.expect(GradleException.class);
        expectedException.expectMessage(containsString("isn't a valid directory"));

        task.get().getShaFiles();
    }

}