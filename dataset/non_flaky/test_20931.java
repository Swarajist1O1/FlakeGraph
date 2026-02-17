class DummyClass_20931 {
    @Test
    public void testBasicAuth() throws Exception {
        BasicAuthLogin login = new BasicAuthLogin();
        login.setUsername("test");
        login.setPassword("pass");
        testSerialization(login);
    }

}