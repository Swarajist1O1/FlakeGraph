class DummyClass_104121 {
	@Test
	public void sunnyDayWithNoSuchLabel() {
		mockRequestResponseWithLabel(
				new ResponseEntity<Void>((Void) null, HttpStatus.NOT_FOUND),
				"nosuchlabel");
		this.locator.setRestTemplate(this.restTemplate);
		assertThat(this.locator.locate(this.environment)).isNull();
	}

}