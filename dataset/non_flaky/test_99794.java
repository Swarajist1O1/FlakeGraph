class DummyClass_99794 {
    @Test
    public void listenOptionalSecureConnection() throws InterruptedException
    {
        ServerEncryptionOptions serverEncryptionOptions = new ServerEncryptionOptions()
                                                          .withOptional(true);
        listen(serverEncryptionOptions, false);
    }

}