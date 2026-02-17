class DummyClass_159523 {
    @Test
    public void failOnTransientFields() throws Exception {
        ASSERT.about(javaSource())
                .that(transientModel)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}