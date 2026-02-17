class DummyClass_77129 {
    @Test
    public void testComplexPropertyNamesV7() throws Exception {
        String schemaPath = "/schema/issue396-v7.json";
        String dataPath = "/data/issue396.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);

        final Set<String> invalidPaths = new HashSet<>();
        node.fields().forEachRemaining(entry -> {
            if (!entry.getValue().asBoolean())
                invalidPaths.add("$." + entry.getKey());
        });

        Set<ValidationMessage> errors = schema.validate(node);
        final Set<String> failedPaths = errors.stream().map(ValidationMessage::getPath).collect(Collectors.toSet());
        Assertions.assertEquals(failedPaths, invalidPaths);
    }

}