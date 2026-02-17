class DummyClass_159539 {
    @Test
    public void failsOnLinkingObjectsWithRequiredFields() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksRequired))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("cannot be @Required");
    }

}