class DummyClass_104126 {
	@Test
	public void interceptorShouldAddHeadersWhenHeadersPropertySet() throws Exception {
		MockClientHttpRequest request = new MockClientHttpRequest();
		ClientHttpRequestExecution execution = Mockito
				.mock(ClientHttpRequestExecution.class);
		byte[] body = new byte[] {};
		Map<String, String> headers = new HashMap<>();
		headers.put("X-Example-Version", "2.1");
		new ConfigServicePropertySourceLocator.GenericRequestHeaderInterceptor(headers)
				.intercept(request, body, execution);
		Mockito.verify(execution).execute(request, body);
		assertThat(request.getHeaders().getFirst("X-Example-Version")).isEqualTo("2.1");
	}

}