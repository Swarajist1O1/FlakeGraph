class DummyClass_159522 {
    @Test
    public void failOnFinalFields() throws Exception {
        ASSERT.about(javaSource())
                .that(finalModel)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}