class DummyClass_133893 {
    @Test
    public void correctly_create_s3_event() throws JsonProcessingException {
        S3Event s3Event = new S3Event("my_bucket", "my_key");

        String output = objectMapper.writeValueAsString(s3Event.asJsonNode());

        assertThat(output, equalTo("{\"Records\":[{\"s3\":" +
                "{\"bucket\":{\"name\":\"my_bucket\"}," +
                "\"object\":{\"key\":\"my_key\"}}}" +
                "]}"));
    }

}