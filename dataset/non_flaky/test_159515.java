class DummyClass_159515 {
    @Test
    public void compileIndexTypes() throws IOException {
        final String[] validIndexFieldTypes = {"byte", "short", "int", "long", "boolean", "String", "java.util.Date",
                "Byte", "Short", "Integer", "Long", "Boolean"};

        for (String fieldType : validIndexFieldTypes) {
            TestRealmObjectFileObject javaFileObject =
                    TestRealmObjectFileObject.getSingleFieldInstance("ValidIndexType", "Index", fieldType, "testField");
            ASSERT.about(javaSource())
                    .that(javaFileObject)
                    .processedWith(new RealmProcessor())
                    .compilesWithoutError();
        }
    }

}