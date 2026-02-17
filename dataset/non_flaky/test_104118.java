class DummyClass_104118 {
	@Test
	public void sunnyDay() {
		Environment body = new Environment("app", "master");
		mockRequestResponseWithoutLabel(new ResponseEntity<>(body, HttpStatus.OK));
		this.locator.setRestTemplate(this.restTemplate);

		ArgumentCaptor<HttpEntity> argumentCaptor = ArgumentCaptor
				.forClass(HttpEntity.class);

		assertThat(this.locator.locate(this.environment)).isNotNull();

		Mockito.verify(this.restTemplate).exchange(anyString(), any(HttpMethod.class),
				argumentCaptor.capture(), any(Class.class), anyString(), anyString());

		HttpEntity httpEntity = argumentCaptor.getValue();
		assertThat(httpEntity.getHeaders().getAccept())
				.containsExactly(MediaType.APPLICATION_JSON);
	}

}