class DummyClass_159499 {
    @Test
    public void compareProcessedAllTypesFile() throws Exception {
        ASSERT.about(javaSource())
                .that(allTypesModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(allTypesDefaultMediator, allTypesDefaultModule,
                        allTypesDefaultMediator, allTypesProxy);
    }

}