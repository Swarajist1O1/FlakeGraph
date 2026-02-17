class DummyClass_53187 {
    @Test
    public void serializeTest() {
        UserInfoResponse response = JsonUtils.readValue(json, UserInfoResponse.class);
        json = JsonUtils.writeValueAsString(response);
        deserializeTest();
    }

}