class DummyClass_104113 {
	@Test
	public void shouldRetryAndFailWithMessageGetConfigServerInstanceFromDiscoveryClient()
			throws Exception {
		givenDiscoveryClientReturnsNoInfo();

		setup("spring.cloud.config.discovery.enabled=true",
				"spring.cloud.config.retry.maxAttempts=3",
				"spring.cloud.config.retry.initialInterval=10",
				"spring.cloud.config.fail-fast=false");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		expectConfigClientPropertiesHasDefaultConfiguration();
	}

}