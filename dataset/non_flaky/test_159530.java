class DummyClass_159530 {
    @Test
    public void compileWithRealmModelFieldInReamlModel() {
        ASSERT.about(javaSource())
                .that(SimpleRealmModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}