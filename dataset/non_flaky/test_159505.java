class DummyClass_159505 {
    @Test
    public void compileAppModuleWrongTypeFail() throws Exception {
        ASSERT.about(javaSources())
                .that(Arrays.asList(allTypesModel, JavaFileObjects.forResource(
                    "some/test/InvalidAllTypesModuleWrongType.java")))
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}