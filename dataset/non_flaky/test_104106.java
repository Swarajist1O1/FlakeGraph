class DummyClass_104106 {
	@Test
	public void multipleInstancesReturnedFromDiscovery() {
		ServiceInstance info1 = new DefaultServiceInstance("app", "localhost", 8888,
				true);
		ServiceInstance info2 = new DefaultServiceInstance("app", "localhost1", 8888,
				false);
		givenDiscoveryClientReturnsInfoForMultipleInstances(info1, info2);

		setup("spring.cloud.config.discovery.enabled=true");

		expectDiscoveryClientConfigServiceBootstrapConfigurationIsSetup();

		verifyDiscoveryClientCalledOnce();
		expectConfigClientPropertiesHasMultipleUris("https://localhost:8888/",
				"http://localhost1:8888/");

	}

}