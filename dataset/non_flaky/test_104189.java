class DummyClass_104189 {
	@Test
	public void testSupportsUnrelatedCredentialItemTypes() {
		CredentialItem usernameCredentialItem = new CredentialItem.Username();

		boolean supportsItems = this.skipSslValidationCredentialsProvider
				.supports(usernameCredentialItem);

		assertThat(supportsItems).as(
				"Credential item types not related to SSL validation skipping should not be supported")
				.isFalse();
	}

}