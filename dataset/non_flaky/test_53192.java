class DummyClass_53192 {
    @Test
    public void serialize_prompts() {
        OIDCIdentityProviderDefinition def = JsonUtils.readValue(defaultJson, OIDCIdentityProviderDefinition.class);
        assertNull(def.getPrompts());
        List<Prompt> prompts = Arrays.asList(new Prompt("username", "text", "Email"),
                new Prompt("password", "password", "Password"),
                new Prompt("passcode", "password", "Temporary Authentication Code (Get on at /passcode)"));
        def.setPrompts(prompts);
        String json = JsonUtils.writeValueAsString(def);
        def = JsonUtils.readValue(json, OIDCIdentityProviderDefinition.class);
        assertEquals(prompts, def.getPrompts());
    }

}