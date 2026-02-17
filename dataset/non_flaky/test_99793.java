class DummyClass_99793 {
    @Test
    public void listenRequiredSecureConnectionWithBroadcastAddrAndLegacyPort() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withInternodeEncryption(ServerEncryptionOptions.InternodeEncryption.all)
                                                          .withOptional(false)
                                                          .withLegacySslStoragePort(true);
        listen(serverEncryptionOptions, true);
    }

}