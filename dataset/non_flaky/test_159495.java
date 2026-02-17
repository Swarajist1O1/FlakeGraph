class DummyClass_159495 {
    @Test
    public void compareProcessedNullTypesFile() throws Exception {
        ASSERT.about(javaSource())
                .that(nullTypesModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(nullTypesProxy);
    }

}