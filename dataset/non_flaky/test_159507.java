class DummyClass_159507 {
    @Test
    public void compileLibraryModuleWrongTypeFail() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource("some/test/InvalidLibraryModuleWrongType.java")))
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}