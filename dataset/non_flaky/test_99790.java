class DummyClass_99790 {
    @Test
    public void listenRequiredSecureConnection() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withOptional(false)
                                                          .withInternodeEncryption(ServerEncryptionOptions.InternodeEncryption.all)
                                                          .withLegacySslStoragePort(false);
        listen(serverEncryptionOptions, false);
    }

}