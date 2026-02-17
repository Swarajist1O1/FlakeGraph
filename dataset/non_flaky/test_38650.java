class DummyClass_38650 {
    @Test
    public void testCredentialProviderPlugin() throws Exception {
        KinesisSink sink = new KinesisSink();

        AWSCredentialsProvider credentialProvider = sink
                .createCredentialProviderWithPlugin(AwsCredentialProviderPluginImpl.class.getName(), "{}")
                .getCredentialProvider();
        Assert.assertNotNull(credentialProvider);
        Assert.assertEquals(credentialProvider.getCredentials().getAWSAccessKeyId(),
                AwsCredentialProviderPluginImpl.accessKey);
        Assert.assertEquals(credentialProvider.getCredentials().getAWSSecretKey(),
                AwsCredentialProviderPluginImpl.secretKey);
        Assert.assertEquals(((BasicSessionCredentials) credentialProvider.getCredentials()).getSessionToken(),
                AwsCredentialProviderPluginImpl.sessionToken);

        sink.close();
    }

}