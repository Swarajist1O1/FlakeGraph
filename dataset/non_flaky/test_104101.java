class DummyClass_104101 {
	@Test
	public void shouldSucceedGetConfigServerInstanceFromDiscoveryClient()
			throws Exception {
		givenDiscoveryClientReturnsInfo();

		setup("spring.cloud.config.discovery.enabled=true",
				"spring.cloud.config.fail-fast=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		expectConfigClientPropertiesHasConfigurationFromEureka();
		verifyDiscoveryClientCalledOnce();
	}

}