class DummyClass_53158 {
    @Test(expected = IllegalArgumentException.class)
    public void emptyKeyId() throws Exception {
        TokenPolicy tokenPolicy = new TokenPolicy();
        tokenPolicy.setKeys(Collections.singletonMap(" ", "signing-key"));
    }

}