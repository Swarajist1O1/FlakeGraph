class DummyClass_104111 {
	@Test
	public void shouldNotRetryIfNotFailFastPropertySet() throws Exception {
		givenDiscoveryClientReturnsInfoOnThirdTry();

		setup("spring.cloud.config.discovery.enabled=true",
				"spring.cloud.config.retry.maxAttempts=3",
				"spring.cloud.config.retry.initialInterval=10");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		verifyDiscoveryClientCalledOnce();
		expectConfigClientPropertiesHasDefaultConfiguration();
	}

}