class DummyClass_159494 {
    @Test
    public void compileProcessedNullTypesFile() throws Exception {
        ASSERT.about(javaSource())
                .that(nullTypesModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}