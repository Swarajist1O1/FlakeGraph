class DummyClass_53161 {
    @Test
    public void deserializationOfTokenPolicyWithNoActiveKeyIdWithMultipleKeys_doesNotFail() {
        String jsonTokenPolicy = "{\"keys\":{\"key-id-1\":{\"signingKey\":\"some-signing-key-1\"},\"key-id-2\":{\"signingKey\":\"some-signing-key-2\"}}}";
        TokenPolicy tokenPolicy = JsonUtils.readValue(jsonTokenPolicy, TokenPolicy.class);
        assertEquals(tokenPolicy.getKeys().get("key-id-1"), "some-signing-key-1");
        assertEquals(tokenPolicy.getKeys().get("key-id-2"), "some-signing-key-2");
    }

}