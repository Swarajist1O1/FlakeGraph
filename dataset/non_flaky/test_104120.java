class DummyClass_104120 {
	@Test
	public void sunnyDayWithLabelThatContainsASlash() {
		Environment body = new Environment("app", "master");
		mockRequestResponseWithLabel(new ResponseEntity<>(body, HttpStatus.OK),
				"release(_)v1.0.0");
		this.locator.setRestTemplate(this.restTemplate);
		TestPropertyValues.of("spring.cloud.config.label:release/v1.0.0")
				.applyTo(this.environment);
		assertThat(this.locator.locate(this.environment)).isNotNull();
	}

}