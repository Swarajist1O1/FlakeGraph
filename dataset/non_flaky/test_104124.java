class DummyClass_104124 {
	@Test
	public void failFastWhenNotFound() throws Exception {
		ClientHttpRequestFactory requestFactory = Mockito
				.mock(ClientHttpRequestFactory.class);
		ClientHttpRequest request = Mockito.mock(ClientHttpRequest.class);
		ClientHttpResponse response = Mockito.mock(ClientHttpResponse.class);
		Mockito.when(requestFactory.createRequest(Mockito.any(URI.class),
				Mockito.any(HttpMethod.class))).thenReturn(request);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		defaults.setFailFast(true);
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		Mockito.when(request.getHeaders()).thenReturn(new HttpHeaders());
		Mockito.when(request.execute()).thenReturn(response);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Mockito.when(response.getHeaders()).thenReturn(headers);
		Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
		Mockito.when(response.getBody())
				.thenReturn(new ByteArrayInputStream("".getBytes()));
		this.locator.setRestTemplate(restTemplate);
		this.expected
				.expectCause(IsInstanceOf.instanceOf(IllegalArgumentException.class));
		this.expected.expectMessage("fail fast property is set");
		this.locator.locate(this.environment);
	}

}