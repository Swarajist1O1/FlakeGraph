class DummyClass_104143 {
	@Test
	public void checkIfExceptionThrownForNegativeIndex() {
		this.locator.setUri(
				new String[] { "http://localhost:8888", "http://localhost:8889" });
		this.expected.expect(IllegalStateException.class);
		this.expected.expectMessage("Trying to access an invalid array index");
		Credentials credentials = this.locator.getCredentials(-1);
	}

}