class DummyClass_104104 {
	@Test
	public void onWhenHeartbeat() throws Exception {
		setup("spring.cloud.config.discovery.enabled=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();

		givenDiscoveryClientReturnsInfo();
		verifyDiscoveryClientCalledOnce();

		this.context.publishEvent(new HeartbeatEvent(this.context, "new"));

		expectConfigClientPropertiesHasConfigurationFromEureka();
	}

}