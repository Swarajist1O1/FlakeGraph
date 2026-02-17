class DummyClass_104141 {
	@Test
	public void changeNameInOverride() {
		this.locator.setName("one");
		ConfigurableEnvironment environment = new StandardEnvironment();
		TestPropertyValues.of("spring.application.name:two").applyTo(environment);
		ConfigClientProperties override = this.locator.override(environment);
		assertThat(override.getName()).isEqualTo("two");
	}

}