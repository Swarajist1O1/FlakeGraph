class DummyClass_159529 {
    @Test
    public void canNotInheritRealmList() throws Exception {
        ASSERT.about(javaSource())
                .that(UseExtendRealmList)
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}