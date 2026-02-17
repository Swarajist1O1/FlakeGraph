class DummyClass_159491 {
    @Test
    public void compileProcessedEmptyFile() throws Exception {
        ASSERT.about(javaSource())
                .that(emptyModel)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}