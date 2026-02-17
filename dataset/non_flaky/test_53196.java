class DummyClass_53196 {
    @Test
    public void testMfaRegisterPageWithoutLoggingIn() {
        webDriver.get(zoneUrl + "/logout.do");
        webDriver.get(zoneUrl + "/login/mfa/register");
        assertEquals(zoneUrl + "/login", webDriver.getCurrentUrl());
    }

}