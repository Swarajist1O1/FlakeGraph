class DummyClass_159534 {
    @Test
    public void failOnLinkingObjectsWithInvalidFieldType() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksInvalidField))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("Fields annotated with @LinkingObjects must be RealmResults");
    }

}