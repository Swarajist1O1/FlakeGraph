class DummyClass_159493 {
    @Test
    public void compareProcessedSimpleFile() throws Exception {
        ASSERT.about(javaSource())
                .that(simpleModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(simpleProxy);
    }

}