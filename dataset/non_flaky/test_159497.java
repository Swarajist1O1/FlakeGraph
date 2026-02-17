class DummyClass_159497 {
    @Test
    public void compileProcessedAllTypesFile() throws Exception {
        ASSERT.about(javaSource())
                .that(allTypesModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}