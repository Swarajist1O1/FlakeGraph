class DummyClass_38648 {
    @Test
    public void testDefaultCredentialProvider() throws Exception {
        KinesisSink sink = new KinesisSink();
        Map<String, String> credentialParam = Maps.newHashMap();
        String awsCredentialPluginParam = new Gson().toJson(credentialParam);
        try {
            sink.defaultCredentialProvider(awsCredentialPluginParam);
            Assert.fail("accessKey and SecretKey validation not applied");
        } catch (IllegalArgumentException ie) {
            // Ok..
        }

        final String accesKey = "ak";
        final String secretKey = "sk";
        credentialParam.put(KinesisSink.ACCESS_KEY_NAME, accesKey);
        credentialParam.put(KinesisSink.SECRET_KEY_NAME, secretKey);
        awsCredentialPluginParam = new Gson().toJson(credentialParam);
        AWSCredentialsProvider credentialProvider = sink.defaultCredentialProvider(awsCredentialPluginParam)
                .getCredentialProvider();
        Assert.assertNotNull(credentialProvider);
        Assert.assertEquals(credentialProvider.getCredentials().getAWSAccessKeyId(), accesKey);
        Assert.assertEquals(credentialProvider.getCredentials().getAWSSecretKey(), secretKey);

        sink.close();
    }

}