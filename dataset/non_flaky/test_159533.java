class DummyClass_159533 {
    @Test
    public void compileBacklinks() {
        ASSERT.about(javaSources())
            .that(Arrays.asList(backlinks, backlinksTarget))
            .processedWith(new RealmProcessor())
            .compilesWithoutError();
    }

}