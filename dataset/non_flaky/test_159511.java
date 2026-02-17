class DummyClass_159511 {
    @Test
    public void compareProcessedBooleansFile() throws Exception {
        ASSERT.about(javaSource())
                .that(booleansModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(booleansProxy);
    }

}