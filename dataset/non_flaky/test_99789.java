class DummyClass_99789 {
    @Test
    public void listenPlainConnectionWithBroadcastAddr() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withInternodeEncryption(ServerEncryptionOptions.InternodeEncryption.none);
        listen(serverEncryptionOptions, true);
    }

}