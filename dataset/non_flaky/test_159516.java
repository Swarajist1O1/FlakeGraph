class DummyClass_159516 {
    @Test
    public void compileInvalidIndexTypes() throws IOException {
        final String[] invalidIndexFieldTypes = {"float", "double", "byte[]", "Simple", "RealmList", "Float", "Double"};

        for (String fieldType : invalidIndexFieldTypes) {
            TestRealmObjectFileObject javaFileObject = TestRealmObjectFileObject.getSingleFieldInstance(
                    "InvalidIndexType", "Index", fieldType, "testField");
            ASSERT.about(javaSource())
                    .that(javaFileObject)
                    .processedWith(new RealmProcessor())
                    .failsToCompile();
        }
    }

}