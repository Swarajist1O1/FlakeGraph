class DummyClass_53225 {
    @Test
    public void failureResponseFromSamlIDP_showErrorFromSaml() throws Exception {
        String zoneId = "testzone3";
        String zoneUrl = baseUrl.replace("localhost",zoneId+".localhost");

        //identity client token
        RestTemplate identityClient = IntegrationTestUtils.getClientCredentialsTemplate(
            IntegrationTestUtils.getClientCredentialsResource(baseUrl, new String[]{"zones.write", "zones.read", "scim.zones"}, "identity", "identitysecret")
        );
        RestTemplate adminClient = IntegrationTestUtils.getClientCredentialsTemplate(
            IntegrationTestUtils.getClientCredentialsResource(baseUrl, new String[0], "admin", "adminsecret")
        );
        //create the zone

        IntegrationTestUtils.createZoneOrUpdateSubdomain(identityClient, baseUrl, zoneId, zoneId, null);

        //create a zone admin user
        String email = new RandomValueStringGenerator().generate() +"@samltesting.org";
        ScimUser user = IntegrationTestUtils.createUser(adminClient, baseUrl,email ,"firstname", "lastname", email, true);
        String groupId = IntegrationTestUtils.findGroupId(adminClient, baseUrl, "zones." + zoneId + ".admin");
        IntegrationTestUtils.addMemberToGroup(adminClient, baseUrl, user.getId(), groupId);

        //get the zone admin token
        String zoneAdminToken =
            IntegrationTestUtils.getAccessTokenByAuthCode(serverRunning,
                UaaTestAccounts.standard(serverRunning),
                "identity",
                "identitysecret",
                email,
                "secr3T");

        SamlIdentityProviderDefinition samlIdentityProviderDefinition = createSimplePHPSamlIDP(SAML_ORIGIN, "testzone3");
        IdentityProvider provider = new IdentityProvider();
        provider.setIdentityZoneId(zoneId);
        provider.setType(OriginKeys.SAML);
        provider.setActive(true);
        provider.setConfig(samlIdentityProviderDefinition);
        provider.setOriginKey(samlIdentityProviderDefinition.getIdpEntityAlias());
        provider.setName("simplesamlphp for testzone2");

        IntegrationTestUtils.createOrUpdateProvider(zoneAdminToken, baseUrl, provider);

        webDriver.get(zoneUrl);
        webDriver.findElement(By.linkText("Login with Simple SAML PHP(simplesamlphp)")).click();
        webDriver.findElement(By.xpath("//h2[contains(text(), 'Enter your username and password')]"));
        webDriver.findElement(By.name("username")).clear();
        webDriver.findElement(By.name("username")).sendKeys(testAccounts.getUserName());
        webDriver.findElement(By.name("password")).sendKeys(testAccounts.getPassword());
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();

        assertEquals("No local entity found for alias invalid, verify your configuration.", webDriver.findElement(By.cssSelector("h2")).getText());
    }

}