class DummyClass_53159 {
    @Test
    public void deserializationOfTokenPolicyWithVerificationKey_doesNotFail() {
        String jsonTokenPolicy = "{\"keys\":{\"key-id-1\":{\"verificationKey\":\"some-verification-key-1\",\"signingKey\":\"some-signing-key-1\"}}}";
        TokenPolicy tokenPolicy = JsonUtils.readValue(jsonTokenPolicy, TokenPolicy.class);
        assertEquals(tokenPolicy.getKeys().get("key-id-1"), "some-signing-key-1");
    }

}