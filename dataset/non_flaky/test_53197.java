class DummyClass_53197 {
    @Test
    public void testMfaVerifyPageWithoutLoggingIn() {
        webDriver.get(zoneUrl + "/logout.do");
        webDriver.get(zoneUrl + "/login/mfa/verify");
        assertEquals(zoneUrl + "/login", webDriver.getCurrentUrl());
    }

}