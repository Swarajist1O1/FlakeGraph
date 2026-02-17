class DummyClass_53180 {
    @Test
    public void testSerialize() {

        MfaProvider<GoogleMfaProviderConfig> provider = createValidGoogleMfaProvider();
        provider.setCreated(new Date());
        provider.setLastModified(new Date());
        String string = JsonUtils.writeValueAsString(provider);
        JsonNode output = JsonUtils.readTree(JsonUtils.writeValueAsString(provider));
        assertEquals(output.get("type").textValue(), MfaProvider.MfaProviderType.GOOGLE_AUTHENTICATOR.toValue());
        JsonNode config = output.get("config");
        assertEquals(config.get("issuer").textValue(), "current-zone");
        assertEquals(config.get("providerDescription").textValue(), "config description");
    }

}