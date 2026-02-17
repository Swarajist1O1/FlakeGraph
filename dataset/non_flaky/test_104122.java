class DummyClass_104122 {
	@Test
	public void failsQuietly() {
		mockRequestResponseWithoutLabel(
				new ResponseEntity<>("Wah!", HttpStatus.INTERNAL_SERVER_ERROR));
		this.locator.setRestTemplate(this.restTemplate);
		assertThat(this.locator.locate(this.environment)).isNull();
	}

}