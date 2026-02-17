class DummyClass_159517 {
    @Test
    public void compilePrimaryKeyTypes() throws IOException {
        final String[] validPrimaryKeyFieldTypes = {"byte", "short", "int", "long", "String", "Byte", "Short", "Integer", "Long"};

        for (String fieldType : validPrimaryKeyFieldTypes) {
            TestRealmObjectFileObject javaFileObject = TestRealmObjectFileObject.getSingleFieldInstance(
                    "ValidPrimaryKeyType", "PrimaryKey", fieldType, "testField");
            ASSERT.about(javaSource())
                    .that(javaFileObject)
                    .processedWith(new RealmProcessor())
                    .compilesWithoutError();
        }
    }

}