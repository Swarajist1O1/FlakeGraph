class DummyClass_53156 {
    @Test(expected = IllegalArgumentException.class)
    public void emptySigningKey() throws Exception {
        TokenPolicy tokenPolicy = new TokenPolicy();
        tokenPolicy.setKeys(Collections.singletonMap("key-id", "             "));
    }

}