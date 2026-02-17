class DummyClass_104099 {
	@Test
	public void shouldFailWithExceptionGetConfigServerInstanceFromDiscoveryClient()
			throws Exception {
		givenDiscoveryClientReturnsNoInfo();

		expectNoInstancesOfConfigServerException();

		setup("spring.cloud.config.discovery.enabled=true",
				"spring.cloud.config.fail-fast=true");
	}

}