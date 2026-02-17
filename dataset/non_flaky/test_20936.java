class DummyClass_20936 {
    @Test(expected = IllegalArgumentException.class)
    public void testSessionIdNull() throws Exception {
        AuthCache.getAuthorizations("");
    }

}