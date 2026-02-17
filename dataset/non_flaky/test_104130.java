class DummyClass_104130 {
	@Test
	public void shouldThrowExceptionWhenNegativeReadTimeoutSet() {
		ConfigClientProperties defaults = new ConfigClientProperties(this.environment);
		defaults.setRequestReadTimeout(-1);
		this.locator = new ConfigServicePropertySourceLocator(defaults);
		this.expected.expect(IllegalStateException.class);
		this.expected.expectMessage("Invalid Value for Read Timeout set.");
		ReflectionTestUtils.invokeMethod(this.locator, "getSecureRestTemplate", defaults);
	}

}