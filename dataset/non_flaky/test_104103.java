class DummyClass_104103 {
	@Test
	public void onWhenRequested() throws Exception {
		givenDiscoveryClientReturnsInfo();

		setup("spring.cloud.config.discovery.enabled=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();
		verifyDiscoveryClientCalledOnce();
		expectConfigClientPropertiesHasConfigurationFromEureka();
	}

}