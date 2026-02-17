class DummyClass_99792 {
    @Test
    public void listenRequiredSecureConnectionWithLegacyPort() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withInternodeEncryption(ServerEncryptionOptions.InternodeEncryption.all)
                                                          .withOptional(false)
                                                          .withLegacySslStoragePort(true);
        listen(serverEncryptionOptions, false);
    }

}