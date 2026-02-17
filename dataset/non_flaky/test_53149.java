class DummyClass_53149 {
    @Test
    public void read_old_json_works() throws Exception {
        read_json(oldJson);
        assertEquals(privateKey, config.getPrivateKey());
        assertEquals(passphrase, config.getPrivateKeyPassword());
        assertEquals(certificate, config.getCertificate());
    }

}