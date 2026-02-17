class DummyClass_159535 {
    @Test
    public void failOnLinkingObjectsWithNonFinalField() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksNonFinalField))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("must be final");
    }

}