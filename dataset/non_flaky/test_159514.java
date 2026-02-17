class DummyClass_159514 {
    @Test
    public void compileCustomAccessor() {
        ASSERT.about(javaSource())
                .that(customAccessorModel)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}