class DummyClass_53198 {
    @Test
    public void testQRCodeValidation() {
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());

        webDriver.findElement(By.id("Next")).click();
        assertEquals(zoneUrl + "/login/mfa/verify", webDriver.getCurrentUrl());
        webDriver.findElement(By.name("code")).sendKeys("1111111111111111112222");

        webDriver.findElement(By.id("verify_code_btn")).click();
        assertEquals("Incorrect code, please try again.", webDriver.findElement(By.cssSelector("form .error-color")).getText());
    }

}