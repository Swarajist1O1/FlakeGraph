class DummyClass_77190 {
    @Test
    public void testWalkWithDifferentListeners() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // This instance of schema contains all listeners.
        ValidationResult result = jsonSchema.walk(
                objectMapper.readTree(getClass().getClassLoader().getResourceAsStream("data/walk-data.json")), false);
        JsonNode collectedNode = (JsonNode) result.getCollectorContext().get(SAMPLE_WALK_COLLECTOR_TYPE);
        assertEquals(collectedNode, (objectMapper.readTree("{" +
                "    \"PROPERTY1\": \"sample1\","
                + "    \"PROPERTY2\": \"sample2\","
                + "    \"property3\": {"
                + "        \"street_address\":\"test-address\","
                + "        \"phone_number\": {"
                + "            \"country-code\": \"091\","
                + "            \"number\": \"123456789\""
                + "          }"
                + "     }"
                + "}")));
        // This instance of schema contains one listener removed.
        CollectorContext collectorContext = result.getCollectorContext();
        collectorContext.reset();
        result = jsonSchema1.walk(
                objectMapper.readTree(getClass().getClassLoader().getResourceAsStream("data/walk-data.json")), false);
        collectedNode = (JsonNode) result.getCollectorContext().get(SAMPLE_WALK_COLLECTOR_TYPE);
        assertEquals(collectedNode, (objectMapper.readTree("{"
                + "    \"property3\": {"
                + "        \"street_address\":\"test-address\","
                + "        \"phone_number\": {"
                + "            \"country-code\": \"091\","
                + "            \"number\": \"123456789\""
                + "          }"
                + "     }"
                + "}")));
    }

}