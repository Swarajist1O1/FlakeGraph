class DummyClass_176816 {
    @Test
    public void shouldCheckFileNameGeneration() throws Exception {
        String filename = "My feature";
        String refactoredName = loadRunnerFeature.createName(filename);
        assertThat(refactoredName, is("My_feature"));
    }

}