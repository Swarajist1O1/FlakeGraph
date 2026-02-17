class DummyClass_53201 {
    @Test
    public void testDisplayIdentityZoneNameOnVerifyPage() {
        performLogin(username);
        webDriver.findElement(By.id("Next")).click();

        assertEquals(zoneUrl + "/login/mfa/verify", webDriver.getCurrentUrl());
        assertEquals(webDriver.findElement(By.id("mfa-identity-zone")).getText(), mfaZone.getName());

        webDriver.findElement(By.id("verify_code_btn")).click();
        assertEquals(webDriver.findElement(By.id("mfa-identity-zone")).getText(), mfaZone.getName());
    }

}