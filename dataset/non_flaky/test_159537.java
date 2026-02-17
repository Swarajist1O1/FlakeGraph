class DummyClass_159537 {
    @Test
    public void failsOnLinkingObjectsMissingFieldName() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksMissingParam))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("must have a parameter identifying the link target");
    }

}