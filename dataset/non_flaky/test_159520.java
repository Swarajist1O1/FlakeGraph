class DummyClass_159520 {
    @Test
    public void compileInvalidRequiredTypes() throws IOException {
        final String[] validPrimaryKeyFieldTypes = {"byte", "short", "int", "long", "float", "double",
                "boolean", "RealmList<Simple>", "Simple"};

        for (String fieldType : validPrimaryKeyFieldTypes) {
            TestRealmObjectFileObject javaFileObject = TestRealmObjectFileObject.getSingleFieldInstance(
                    "ValidPrimaryKeyType", "Required", fieldType, "testField");
            ASSERT.about(javaSource())
                    .that(javaFileObject)
                    .processedWith(new RealmProcessor())
                    .failsToCompile();
        }
    }

}