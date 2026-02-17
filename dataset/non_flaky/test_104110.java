class DummyClass_104110 {
	@Test
	public void shouldRetryAndSucceedGetConfigServerInstanceFromDiscoveryClient()
			throws Exception {
		givenDiscoveryClientReturnsInfoOnThirdTry();

		setup("spring.cloud.config.discovery.enabled=true",
				"spring.cloud.config.retry.maxAttempts=3",
				"spring.cloud.config.retry.initialInterval=10",
				"spring.cloud.config.fail-fast=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		verifyDiscoveryClientCalledThreeTimes();

		this.context.publishEvent(new HeartbeatEvent(this.context, "new"));

		expectConfigClientPropertiesHasConfigurationFromEureka();
	}

}