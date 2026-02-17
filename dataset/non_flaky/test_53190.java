class DummyClass_53190 {
    @Test
    public void testEquals2() {
        ExternalIdentityProviderDefinition def = new ExternalIdentityProviderDefinition();
        def.setStoreCustomAttributes(false);
        assertFalse(definition.equals(def));
    }

}