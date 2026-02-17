class DummyClass_104107 {
	@Test
	public void setsPasssword() throws Exception {
		this.info.getMetadata().put("password", "bar");
		givenDiscoveryClientReturnsInfo();

		setup("spring.cloud.config.discovery.enabled=true");

		ConfigClientProperties locator = this.context
				.getBean(ConfigClientProperties.class);
		Credentials credentials = locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://foo:8877/");
		assertThat(credentials.getPassword()).isEqualTo("bar");
		assertThat(credentials.getUsername()).isEqualTo("user");
	}

}