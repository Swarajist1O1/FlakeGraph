class DummyClass_99788 {
    @Test
    public void listenPlainConnection() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withInternodeEncryption(ServerEncryptionOptions.InternodeEncryption.none);
        listen(serverEncryptionOptions, false);
    }

}