class DummyClass_53199 {
    @Test
    public void checkAccessForTotpPage() throws Exception {
        webDriver.get(zoneUrl + "/logout.do");
        webDriver.manage().deleteAllCookies();
        webDriver.get(zoneUrl + "/login/mfa/register");
        assertEquals(zoneUrl + "/login", webDriver.getCurrentUrl());
    }

}