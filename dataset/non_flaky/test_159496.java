class DummyClass_159496 {
    @Test
    public void compileAllTypesFile() {
        ASSERT.about(javaSource())
                .that(allTypesModel)
                .compilesWithoutError();
    }

}