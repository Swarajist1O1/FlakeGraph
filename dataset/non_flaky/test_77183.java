class DummyClass_77183 {
    @Test
    public void shouldWalkAnyOfPropertiesWithWithPayloadAndValidation() throws Exception {
        JsonNode data = getJsonNodeFromStreamContent(Issue451Test.class.getResourceAsStream(
                "/data/issue451.json"));
        walk(data,true);
    }

}