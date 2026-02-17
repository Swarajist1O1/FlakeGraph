class DummyClass_159527 {
    @Test
    public void failOnInvalidRealmModel_3() throws Exception {
        ASSERT.about(javaSource())
                .that(invalidRealmModelModel_3)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}