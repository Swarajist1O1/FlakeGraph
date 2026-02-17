class DummyClass_97739 {
    @Test
    public void testJacksonDuplicateProperty() throws JsonProcessingException {
        final DuplicateKind1 object = new DuplicateKind1();
        object.kind = "kind_invalid";
        final String json = new ObjectMapper().writeValueAsString(object);
        // {"kind":"kind_1","kind":"kind_invalid"}
        Assert.assertTrue(json.contains("\"kind\":\"kind_1\""));
        Assert.assertTrue(json.contains("\"kind\":\"kind_invalid\""));
    }

}