class DummyClass_38649 {
    @Test
    public void testCredentialProvider() throws Exception {
        KinesisSink sink = new KinesisSink();

        final String accesKey = "ak";
        final String secretKey = "sk";
        Map<String, String> credentialParam = Maps.newHashMap();
        credentialParam.put(KinesisSink.ACCESS_KEY_NAME, accesKey);
        credentialParam.put(KinesisSink.SECRET_KEY_NAME, secretKey);
        String awsCredentialPluginParam = new Gson().toJson(credentialParam);
        AWSCredentialsProvider credentialProvider = sink.createCredentialProvider(null, awsCredentialPluginParam)
                .getCredentialProvider();
        Assert.assertEquals(credentialProvider.getCredentials().getAWSAccessKeyId(), accesKey);
        Assert.assertEquals(credentialProvider.getCredentials().getAWSSecretKey(), secretKey);

        credentialProvider = sink.createCredentialProvider(AwsCredentialProviderPluginImpl.class.getName(), "{}")
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