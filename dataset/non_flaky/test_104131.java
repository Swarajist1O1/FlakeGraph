class DummyClass_104131 {
	@Test
	public void shouldThrowExceptionWhenNegativeConnectTimeoutSet() {
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		defaults.setRequestConnectTimeout(-1);
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		this.expected.expect(IllegalStateException.class);
		this.expected.expectMessage("Invalid Value for Connect Timeout set.");
		ReflectionTestUtils.invokeMethod(this.locator, "getSecureRestTemplate", defaults);
	}

}