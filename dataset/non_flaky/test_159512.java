class DummyClass_159512 {
    @Test
    public void compileMissingGenericType() {
        ASSERT.about(javaSource())
                .that(missingGenericTypeModel)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}