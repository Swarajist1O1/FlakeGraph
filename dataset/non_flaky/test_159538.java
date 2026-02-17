class DummyClass_159538 {
    @Test
    public void failsOnLinkingObjectsMissingGeneric() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksMissingGeneric))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("must specify a generic type");
    }

}