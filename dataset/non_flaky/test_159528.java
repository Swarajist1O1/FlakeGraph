class DummyClass_159528 {
    @Test
    public void validRealmModelUsingInheritance() throws Exception {
        ASSERT.about(javaSource())
                .that(ValidModelPojo_ExtendingRealmObject)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}