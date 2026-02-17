class DummyClass_53150 {
    @Test
    public void to_json_ignores_legacy_values() throws Exception {
        read_json(oldJson);
        String json = JsonUtils.writeValueAsString(config);
        read_json(json);
        assertEquals(privateKey, config.getPrivateKey());
        assertEquals(passphrase, config.getPrivateKeyPassword());
        assertEquals(certificate, config.getCertificate());
    }

}