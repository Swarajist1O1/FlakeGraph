class DummyClass_159521 {
    @Test
    public void compileConflictingFieldName() throws Exception {
        ASSERT.about(javaSource())
                .that(conflictingFieldNameModel)
                .processedWith(new RealmProcessor())
                .compilesWithoutError();
    }

}