class DummyClass_53224 {
    @Test
    public void testSimpleSamlLoginWithAddShadowUserOnLoginFalse() throws Exception {
        // Deleting marissa@test.org from simplesamlphp because previous SAML authentications automatically
        // create a UAA user with the email address as the username.
        deleteUser(SAML_ORIGIN, testAccounts.getEmail());

        IdentityProvider provider = IntegrationTestUtils.createIdentityProvider(SAML_ORIGIN, false, baseUrl, serverRunning);
        String clientId = "app-addnew-false"+ new RandomValueStringGenerator().generate();
        String redirectUri = "http://nosuchhostname:0/nosuchendpoint";
        BaseClientDetails client = createClientAndSpecifyProvider(clientId, provider, redirectUri);

        String firstUrl = "/oauth/authorize?"
                + "client_id=" + clientId
                + "&response_type=code"
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8");

        webDriver.get(baseUrl + firstUrl);
        webDriver.findElement(By.xpath("//h2[contains(text(), 'Enter your username and password')]"));
        webDriver.findElement(By.name("username")).clear();
        webDriver.findElement(By.name("username")).sendKeys(testAccounts.getUserName());
        webDriver.findElement(By.name("password")).sendKeys(testAccounts.getPassword());
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();

        // We need to verify the last request URL through the performance log because the redirect
        // URI does not exist. When the webDriver follows the non-existent redirect URI it receives a
        // connection refused error so webDriver.getCurrentURL() will remain as the SAML IdP URL.

        List<LogEntry> logEntries = webDriver.manage().logs().get(LogType.PERFORMANCE).getAll();
        List<String> logMessages = logEntries.stream().map(logEntry -> logEntry.getMessage()).collect(Collectors.toList());

        assertThat(logMessages, hasItem(containsString(redirectUri + "?error=access_denied&error_description=SAML+user+does+not+exist.+You+can+correct+this+by+creating+a+shadow+user+for+the+SAML+user.")));
    }

}