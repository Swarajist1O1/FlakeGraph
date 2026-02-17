class DummyClass_159526 {
    @Test
    public void failOnInvalidRealmModel_2() throws Exception {
        ASSERT.about(javaSource())
                .that(invalidRealmModelModel_2)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}