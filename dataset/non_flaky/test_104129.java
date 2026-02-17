class DummyClass_104129 {
	@Test
	public void shouldThrowExceptionWhenPasswordAndAuthorizationBothSet() {
		HttpHeaders headers = new HttpHeaders();
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		defaults.getHeaders().put(AUTHORIZATION, "Basic dXNlcm5hbWU6cGFzc3dvcmQNCg==");
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		String username = "user";
		String password = "pass";
		this.expected.expect(IllegalStateException.class);
		this.expected.expectMessage("You must set either 'password' or 'authorization'");
		ReflectionTestUtils.invokeMethod(this.locator, "addAuthorizationToken", defaults,
				headers, username, password);
	}

}