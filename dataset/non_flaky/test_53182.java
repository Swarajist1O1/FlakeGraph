class DummyClass_53182 {
    @Test
    public void testDeserializeInvalidType() {
        String json = "{\n" +
                "  \"type\" : \"invalid-type\",\n" +
                "  \"config\" : {\n" +
                "    \"providerDescription\" : \"ddd\",\n" +
                "    \"issuer\": \"issuer\",\n" +
                "    \"algorithm\": \"SHA256\",\n" +
                "    \"digits\": 8, \n" +
                "    \"duration\": 32 \n" +
                "  },\n" +
                "  \"name\" : \"UAA Provider\" \n" +
                "}";

        MfaProvider<GoogleMfaProviderConfig> provider = JsonUtils.readValue(json, MfaProvider.class);

        assertEquals(null, provider.getType());
        assertEquals("UAA Provider", provider.getName());
        assertNull(provider.getConfig());
    }

}