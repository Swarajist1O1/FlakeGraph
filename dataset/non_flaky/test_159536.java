class DummyClass_159536 {
    @Test
    public void failsOnLinkingObjectsWithLinkedFields() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksLinked))
            .processedWith(new RealmProcessor())
            .failsToCompile()
            .withErrorContaining("The use of '.' to specify fields in referenced classes is not supported");
    }

}