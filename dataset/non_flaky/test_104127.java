class DummyClass_104127 {
	@Test
	public void shouldAddAuthorizationHeaderWhenPasswordSet() {
		HttpHeaders headers = new HttpHeaders();
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		String username = "user";
		String password = "pass";
		ReflectionTestUtils.invokeMethod(this.locator, "addAuthorizationToken", defaults,
				headers, username, password);
		assertThat(headers).hasSize(1);
	}

}