class DummyClass_159518 {
    @Test
    public void compileInvalidPrimaryKeyTypes() throws IOException {
        final String[] invalidPrimaryKeyFieldTypes = {"boolean", "java.util.Date", "Simple", "RealmList<Simple>", "Boolean"};

        for (String fieldType : invalidPrimaryKeyFieldTypes) {
            TestRealmObjectFileObject javaFileObject =
                    TestRealmObjectFileObject.getSingleFieldInstance(
                            "InvalidPrimaryKeyType", "PrimaryKey", fieldType, "testField");
            ASSERT.about(javaSource())
                    .that(javaFileObject)
                    .processedWith(new RealmProcessor())
                    .failsToCompile();
        }
    }

}