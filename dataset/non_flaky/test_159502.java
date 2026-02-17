class DummyClass_159502 {
    @Test
    public void compileLibraryModulesAllClasses() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource("some/test/LibraryModuleAllClasses.java")))
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}