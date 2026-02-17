class DummyClass_104108 {
	@Test
	public void setsPath() throws Exception {
		this.info.getMetadata().put("configPath", "/bar");
		givenDiscoveryClientReturnsInfo();

		setup("spring.cloud.config.discovery.enabled=true");

		expectConfigClientPropertiesHasConfiguration("http://foo:8877/bar");
	}

}