class DummyClass_53148 {
    @Test
    public void testSetKeyAndCert() throws CertificateException {
        config.setPrivateKey(privateKey);
        config.setPrivateKeyPassword(passphrase);
        config.setCertificate(certificate);
        assertEquals(privateKey, config.getPrivateKey());
        assertEquals(passphrase, config.getPrivateKeyPassword());
    }

}