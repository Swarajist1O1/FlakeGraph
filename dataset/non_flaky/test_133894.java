class DummyClass_133894 {
    @Test
    public void correctly_create_sns_event() throws JsonProcessingException {
        SNSEvent snsEvent = new SNSEvent("{ \"key\": \"value\" }");

        String output = objectMapper.writeValueAsString(snsEvent.asJsonNode());

        assertThat(output, equalTo("{\"Records\":[{\"Sns\":" +
                "{\"Message\":\"{ \\\"key\\\": \\\"value\\\" }\"}}" +
                "]}"));
    }

}