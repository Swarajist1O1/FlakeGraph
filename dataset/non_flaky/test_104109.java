class DummyClass_104109 {
	@Test
	public void shouldFailGetConfigServerInstanceFromDiscoveryClient() throws Exception {
		givenDiscoveryClientReturnsNoInfo();

		setup("spring.cloud.config.discovery.enabled=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		verifyDiscoveryClientCalledOnce();
		expectConfigClientPropertiesHasDefaultConfiguration();
	}

}