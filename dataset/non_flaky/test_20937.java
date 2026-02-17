class DummyClass_20937 {
    @Test
    public void testGetAuths() throws Exception {
        Authorizations a = AuthCache.getAuthorizations(cookie);
        Assert.assertEquals("A,B,C", a.toString());
    }

}