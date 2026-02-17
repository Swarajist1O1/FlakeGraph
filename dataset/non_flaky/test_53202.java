class DummyClass_53202 {
    @Test
    public void testManualMfaRegistrationFlow() {
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        webDriver.findElement(By.linkText("manual setup instructions")).click();

        assertEquals(zoneUrl + "/login/mfa/manual", webDriver.getCurrentUrl());

        String key = webDriver.findElement(By.id("key")).getText();
        String account = webDriver.findElement(By.id("account")).getText();
        assertFalse("secret not found", key.isEmpty());
        assertFalse("account not found", account.isEmpty());

        webDriver.findElement(By.id("Next")).click();
        assertEquals(zoneUrl + "/login/mfa/verify", webDriver.getCurrentUrl());

        verifyCodeOnRegistration(key, "/");
    }

}