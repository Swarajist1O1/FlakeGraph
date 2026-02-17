class DummyClass_104132 {
	@Test
	public void checkInterceptorHasNoAuthorizationHeaderPresent() {
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		defaults.getHeaders().put(AUTHORIZATION, "Basic dXNlcm5hbWU6cGFzc3dvcmQNCg==");
		defaults.getHeaders().put("key", "value");
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		RestTemplate restTemplate = ReflectionTestUtils.invokeMethod(this.locator,
				"getSecureRestTemplate", defaults);
		Iterator<ClientHttpRequestInterceptor> iterator = restTemplate.getInterceptors()
				.iterator();
		while (iterator.hasNext()) {
			GenericRequestHeaderInterceptor genericRequestHeaderInterceptor = (GenericRequestHeaderInterceptor) iterator
					.next();
			assertThat(genericRequestHeaderInterceptor.getHeaders().get(AUTHORIZATION))
					.isEqualTo(null);
		}
	}

}