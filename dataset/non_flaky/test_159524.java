class DummyClass_159524 {
    @Test
    public void failOnVolatileFields() throws Exception {
        ASSERT.about(javaSource())
                .that(volatileModel)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}