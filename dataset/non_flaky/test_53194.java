class DummyClass_53194 {
    @Test
    public void force_password_happens_after_MFA() throws Exception {
        IntegrationTestUtils.updateUserToForcePasswordChange(
            getRestTemplate(),
            baseUrl,
            adminAccessToken,
            user.getId(),
            mfaZone.getId()
        );

        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        String imageSrc = webDriver.findElement(By.id("qr")).getAttribute("src");

        String secretKey = getSecretFromQrImageString(imageSrc);

        webDriver.findElement(By.id("Next")).click();
        verifyCodeOnRegistration(secretKey, "/force_password_change");


    }

}