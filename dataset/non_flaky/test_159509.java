class DummyClass_159509 {
    @Test
    public void compileProcessedBooleansFile() throws Exception {
        ASSERT.about(javaSource())
                .that(booleansModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}