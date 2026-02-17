class DummyClass_104146 {
	@Test
	public void withHealthIndicator() {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				PropertySourceBootstrapConfiguration.class,
				ConfigServiceBootstrapConfiguration.class)
						.child(ConfigClientAutoConfiguration.class)
						.web(WebApplicationType.NONE).run();
		assertThat(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
				ConfigClientProperties.class).length).isEqualTo(1);
		assertThat(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
				ConfigServerHealthIndicator.class).length).isEqualTo(1);
		context.close();
	}

}