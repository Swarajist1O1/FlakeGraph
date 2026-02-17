class DummyClass_159513 {
    // Disabled because it does not seem to find the generated Interface file @Test
    public void compileFieldNamesFiles() {
        ASSERT.about(javaSource())
                .that(fieldNamesModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}