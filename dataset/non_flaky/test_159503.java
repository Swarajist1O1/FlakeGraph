class DummyClass_159503 {
    @Test
    public void compileLibraryModulesCustomClasses() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource("some/test/LibraryModuleCustomClasses.java")))
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}