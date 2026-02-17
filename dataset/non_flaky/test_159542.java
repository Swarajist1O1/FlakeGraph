class DummyClass_159542 {
    @Test
    public void failsOnLinkingObjectsWithFieldWrongType() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksWrongType))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("instead of");
    }

}