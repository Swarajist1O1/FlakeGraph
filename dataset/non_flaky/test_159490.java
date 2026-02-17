class DummyClass_159490 {
    @Test
    public void compileProcessedSimpleFile() throws Exception {
        ASSERT.about(javaSource())
                .that(simpleModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}