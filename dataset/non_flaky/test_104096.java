class DummyClass_104096 {
	@Test
	public void overrideConfigServicePropertySourceLocatorWhenBeanIsProvided() {
		TestPropertyValues.of("spring.cloud.config.enabled=true").applyTo(this.context);
		this.context.register(ConfigServicePropertySourceLocatorOverrideConfig.class);
		this.context.register(ConfigServiceBootstrapConfiguration.class);
		this.context.refresh();

		ConfigServicePropertySourceLocator locator = this.context
				.getBean(ConfigServicePropertySourceLocator.class);

		Field restTemplateField = ReflectionUtils
				.findField(ConfigServicePropertySourceLocator.class, "restTemplate");
		restTemplateField.setAccessible(true);

		RestTemplate restTemplate = (RestTemplate) ReflectionUtils
				.getField(restTemplateField, locator);

		assertThat(restTemplate).isNotNull();
	}

}