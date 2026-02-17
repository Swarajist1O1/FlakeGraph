class DummyClass_104098 {
	@Test
	public void withParent() {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				ConfigClientAutoConfiguration.class).child(Object.class)
						.web(WebApplicationType.NONE).run();
		assertThat(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
				ConfigClientProperties.class).length).isEqualTo(1);
		context.close();
	}

}