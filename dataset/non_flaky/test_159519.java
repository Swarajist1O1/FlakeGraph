class DummyClass_159519 {
    @Test
    public void compileRequiredTypes() throws IOException {
        final String[] validPrimaryKeyFieldTypes = {"Byte", "Short", "Integer", "Long", "String",
                "Float", "Double", "Boolean", "java.util.Date"};

        for (String fieldType : validPrimaryKeyFieldTypes) {
            TestRealmObjectFileObject javaFileObject = TestRealmObjectFileObject.getSingleFieldInstance(
                    "ValidPrimaryKeyType", "Required", fieldType, "testField");
            ASSERT.about(javaSource())
                    .that(javaFileObject)
                    .processedWith(new RealmProcessor())
                    .compilesWithoutError();
        }
    }

}