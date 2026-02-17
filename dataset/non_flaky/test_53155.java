class DummyClass_53155 {
    @Test(expected = IllegalArgumentException.class)
    public void nullSigningKey() throws Exception {
        TokenPolicy tokenPolicy = new TokenPolicy();
        tokenPolicy.setKeys(Collections.singletonMap("key-id", null));
    }

}