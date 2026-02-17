class DummyClass_53205 {
    @Test
    public void testQRCodeScreen_ClickManualClickNextClickBack() throws Exception{
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        webDriver.findElement(By.linkText("manual setup instructions")).click();
        assertEquals(zoneUrl + "/login/mfa/manual", webDriver.getCurrentUrl());

        webDriver.findElement(By.id("Next")).click();
        assertEquals(zoneUrl + "/login/mfa/verify", webDriver.getCurrentUrl());

        webDriver.findElement(By.id("Back")).click();
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        String imageSrc = webDriver.findElement(By.id("qr")).getAttribute("src");

        String secretKey = getSecretFromQrImageString(imageSrc);

        assertFalse("secret not found", secretKey.isEmpty());

        webDriver.findElement(By.id("Next")).click();
        verifyCodeOnRegistration(secretKey, "/");
    }

}