class DummyClass_159541 {
    @Test
    public void failsOnLinkingObjectsFieldNotFound() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksNotFound))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("does not exist in class");
    }

}