class DummyClass_53203 {
    @Test
    public void testQRCodeScreen_ClickManualAndReturn() throws Exception{
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        webDriver.findElement(By.linkText("manual setup instructions")).click();
        assertEquals(zoneUrl + "/login/mfa/manual", webDriver.getCurrentUrl());

        webDriver.findElement(By.id("Back")).click();
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        String imageSrc = webDriver.findElement(By.id("qr")).getAttribute("src");

        String secretKey = getSecretFromQrImageString(imageSrc);

        webDriver.findElement(By.id("Next")).click();
        verifyCodeOnRegistration(secretKey, "/");
    }

}