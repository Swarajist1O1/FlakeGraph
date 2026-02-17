class DummyClass_159501 {
    @Test
    public void compileAppModuleAllClasses() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource("some/test/AppModuleAllClasses.java")))
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}