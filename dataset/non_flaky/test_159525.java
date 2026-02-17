class DummyClass_159525 {
    @Test
    public void failOnInvalidRealmModel_1() throws Exception {
        ASSERT.about(javaSource())
                .that(invalidRealmModelModel_1)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}