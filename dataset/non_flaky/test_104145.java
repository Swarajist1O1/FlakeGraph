class DummyClass_104145 {
	@Test
	public void checkIfExceptionThrownForIndexEqualToLength() {
		this.locator.setUri(
				new String[] { "http://localhost:8888", "http://localhost:8889" });
		this.expected.expect(IllegalStateException.class);
		this.expected.expectMessage("Trying to access an invalid array index");
		Credentials credentials = this.locator.getCredentials(2);
	}

}