class DummyClass_104097 {
	@Test
	public void sunnyDay() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				ConfigClientAutoConfiguration.class);
		assertThat(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,
				ConfigClientProperties.class).length).isEqualTo(1);
		context.close();
	}

}