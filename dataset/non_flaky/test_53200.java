class DummyClass_53200 {
    @Test
    public void testDisplayIdentityZoneNameOnRegisterPage() {
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        assertEquals(webDriver.findElement(By.id("mfa-identity-zone")).getText(), mfaZone.getName());
    }

}