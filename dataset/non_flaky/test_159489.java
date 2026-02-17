class DummyClass_159489 {
    @Test
    public void compileSimpleFile() {
        ASSERT.about(javaSource())
                .that(simpleModel)
                .compilesWithoutError();
    }

}