class DummyClass_99791 {
    @Test
    public void listenRequiredSecureConnectionWithBroadcastAddr() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withOptional(false)
                                                          .withInternodeEncryption(ServerEncryptionOptions.InternodeEncryption.all)
                                                          .withLegacySslStoragePort(false);
        listen(serverEncryptionOptions, true);
    }

}