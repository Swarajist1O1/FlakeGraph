class DummyClass_53157 {
    @Test(expected = IllegalArgumentException.class)
    public void nullKeyId() throws Exception {
        TokenPolicy tokenPolicy = new TokenPolicy();
        tokenPolicy.setKeys(Collections.singletonMap(null, "signing-key"));
    }

}