class DummyClass_53143 {
    @Test
    public void legacy_key_is_part_of_map() {
        config.setPrivateKey(privateKey);
        config.setPrivateKeyPassword(passphrase);
        config.setCertificate(certificate);
        Map<String, SamlKey> keys = config.getKeys();
        assertEquals(1, keys.size());
        assertNotNull(keys.get(LEGACY_KEY_ID));
        assertEquals(privateKey, keys.get(LEGACY_KEY_ID).getKey());
        assertEquals(passphrase, keys.get(LEGACY_KEY_ID).getPassphrase());
        assertEquals(certificate, keys.get(LEGACY_KEY_ID).getCertificate());
    }

}