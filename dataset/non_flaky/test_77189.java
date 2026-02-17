class DummyClass_77189 {
    @Test
    public void testWalk() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
    }

}