class DummyClass_104128 {
	@Test
	public void shouldAddAuthorizationHeaderWhenAuthorizationSet() {
		HttpHeaders headers = new HttpHeaders();
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		defaults.getHeaders().put(AUTHORIZATION, "Basic dXNlcm5hbWU6cGFzc3dvcmQNCg==");
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		String username = "user";
		String password = null;
		ReflectionTestUtils.invokeMethod(this.locator, "addAuthorizationToken", defaults,
				headers, username, password);
		assertThat(headers).hasSize(1);
	}

}