class DummyClass_53188 {
    @Test
    public void testEquals() {
        ExternalIdentityProviderDefinition definition1 = new ExternalIdentityProviderDefinition();
        definition1.setAddShadowUserOnLogin(true);
        ExternalIdentityProviderDefinition definition2 = new ExternalIdentityProviderDefinition();
        definition2.setAddShadowUserOnLogin(false);

        assertNotEquals(definition1, definition2);
        definition2.setAddShadowUserOnLogin(true);
        assertEquals(definition1, definition2);
    }

}