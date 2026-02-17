class DummyClass_53144 {
    @Test
    public void addActiveKey() {
        SamlKey key = new SamlKey(privateKey, passphrase, certificate);
        String keyId = "testKeyId";
        config.addAndActivateKey(keyId, key);
        Map<String, SamlKey> keys = config.getKeys();
        assertNotNull(keys);
        assertEquals(1, keys.size());
        assertEquals(keyId, config.getActiveKeyId());
        assertNotNull(keys.get(keyId));
        assertEquals(privateKey, keys.get(keyId).getKey());
        assertEquals(passphrase, keys.get(keyId).getPassphrase());
        assertEquals(certificate, keys.get(keyId).getCertificate());
    }

}