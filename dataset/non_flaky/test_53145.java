class DummyClass_53145 {
    @Test
    public void addNonActive() {
        addActiveKey();
        SamlKey key = new SamlKey(privateKey, passphrase, certificate);
        String keyId = "nonActiveKeyId";
        config.addKey(keyId, key);
        Map<String, SamlKey> keys = config.getKeys();
        assertNotNull(keys);
        assertEquals(2, keys.size());
        assertNotEquals(keyId, config.getActiveKeyId());
        assertNotNull(keys.get(keyId));
        assertEquals(privateKey, keys.get(keyId).getKey());
        assertEquals(passphrase, keys.get(keyId).getPassphrase());
        assertEquals(certificate, keys.get(keyId).getCertificate());
    }

}