class DummyClass_53181 {
    @Test
    public void testDeserialize() {
        String json = "{\n" +
                "  \"type\" : \"google-authenticator\",\n" +
                "  \"config\" : {\n" +
                "    \"providerDescription\" : \"ddd\",\n" +
                "    \"issuer\": \"issuer\",\n" +
                "    \"algorithm\": \"SHA256\",\n" +
                "    \"digits\": 8, \n" +
                "    \"duration\": 32 \n" +
                "  },\n" +
                "  \"name\" : \"UAA Provider\",  \n" +
                "  \"active\" : true\n" +
                "}";

        MfaProvider<GoogleMfaProviderConfig> provider = JsonUtils.readValue(json, MfaProvider.class);

        assertEquals(MfaProvider.MfaProviderType.GOOGLE_AUTHENTICATOR, provider.getType());
        assertEquals("UAA Provider", provider.getName());
        GoogleMfaProviderConfig config = provider.getConfig();
        assertEquals("issuer", config.getIssuer());
        assertEquals("ddd", config.getProviderDescription());
    }

}