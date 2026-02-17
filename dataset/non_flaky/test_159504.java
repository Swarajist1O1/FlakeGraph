class DummyClass_159504 {
    @Test
    public void compileAppModuleMixedParametersFail() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource(
                    "some/test/InvalidAllTypesModuleMixedParameters.java")))
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}