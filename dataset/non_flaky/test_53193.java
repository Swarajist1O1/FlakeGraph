class DummyClass_53193 {
    @Test
    public void testQRCodeScreen() throws Exception {
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        String imageSrc = webDriver.findElement(By.id("qr")).getAttribute("src");

        String secretKey = getSecretFromQrImageString(imageSrc);

        webDriver.findElement(By.id("Next")).click();
        verifyCodeOnRegistration(secretKey, "/");
    }

}