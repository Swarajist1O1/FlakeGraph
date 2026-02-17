class DummyClass_159540 {
    @Test
    public void failsOnLinkingObjectsWithIgnoreFields() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget, backlinksIgnored))
            .processedWith(new RealmProcessor())
            .compilesWithoutError();
    }

}