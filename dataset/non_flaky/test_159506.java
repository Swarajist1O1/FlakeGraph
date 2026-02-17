class DummyClass_159506 {
    @Test
    public void compileLibraryModuleMixedParametersFail() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource("some/test/InvalidLibraryModuleMixedParameters.java")))
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}