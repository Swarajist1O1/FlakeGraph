class DummyClass_104102 {
	@Test
	public void offByDefault() throws Exception {
		this.context = new AnnotationConfigApplicationContext(
				DiscoveryClientConfigServiceBootstrapConfiguration.class);

		assertThat(this.context.getBeanNamesForType(DiscoveryClient.class).length)
				.isEqualTo(0);
		assertThat(this.context.getBeanNamesForType(
				DiscoveryClientConfigServiceBootstrapConfiguration.class).length)
						.isEqualTo(0);
	}

}