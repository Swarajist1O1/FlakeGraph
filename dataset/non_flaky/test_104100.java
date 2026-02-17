class DummyClass_104100 {
	@Test
	public void shouldFailWithMessageGetConfigServerInstanceFromDiscoveryClient()
			throws Exception {
		givenDiscoveryClientReturnsNoInfo();

		setup("spring.cloud.config.discovery.enabled=true",
				"spring.cloud.config.fail-fast=false");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		expectConfigClientPropertiesHasDefaultConfiguration();
		verifyDiscoveryClientCalledOnce();
	}

}