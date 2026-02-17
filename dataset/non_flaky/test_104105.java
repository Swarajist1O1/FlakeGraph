class DummyClass_104105 {
	@Test
	public void secureWhenRequested() throws Exception {
		this.info = new DefaultServiceInstance("app", "foo", 443, true);
		givenDiscoveryClientReturnsInfo();

		setup("spring.cloud.config.discovery.enabled=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();

		verifyDiscoveryClientCalledOnce();
		expectConfigClientPropertiesHasConfiguration("https://foo:443/");
	}

}