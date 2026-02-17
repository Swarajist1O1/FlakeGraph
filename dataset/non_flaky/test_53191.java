class DummyClass_53191 {
    @Test
    public void serialize_discovery_url() throws MalformedURLException {
        OIDCIdentityProviderDefinition def = JsonUtils.readValue(defaultJson, OIDCIdentityProviderDefinition.class);
        assertNull(def.getDiscoveryUrl());
        def.setDiscoveryUrl(new URL(url));
        assertEquals(url, def.getDiscoveryUrl().toString());
        String json = JsonUtils.writeValueAsString(def);
        def = JsonUtils.readValue(json, OIDCIdentityProviderDefinition.class);
        assertEquals(url, def.getDiscoveryUrl().toString());
    }

}