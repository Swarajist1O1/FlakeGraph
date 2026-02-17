class DummyClass_104119 {
	@Test
	public void sunnyDayWithLabel() {
		Environment body = new Environment("app", "master");
		mockRequestResponseWithLabel(new ResponseEntity<>(body, HttpStatus.OK), "v1.0.0");
		this.locator.setRestTemplate(this.restTemplate);
		TestPropertyValues.of("spring.cloud.config.label:v1.0.0")
				.applyTo(this.environment);
		assertThat(this.locator.locate(this.environment)).isNotNull();
	}

}