class DummyClass_99795 {
    @Test
    public void listenOptionalSecureConnectionWithBroadcastAddr() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withOptional(true);
        listen(serverEncryptionOptions, true);
    }

}