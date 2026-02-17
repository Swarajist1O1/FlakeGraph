class DummyClass_104190 {
	@Test
	public void testSupportsUnrelatedCredentialItemTypesWithDelegate() {
		this.skipSslValidationCredentialsProvider = new GitSkipSslValidationCredentialsProvider(
				this.mockDelegateCredentialsProvider);
		CredentialItem usernameCredentialItem = new CredentialItem.Username();

		when(this.mockDelegateCredentialsProvider.supports(usernameCredentialItem))
				.thenReturn(true);

		boolean supportsItems = this.skipSslValidationCredentialsProvider
				.supports(usernameCredentialItem);

		assertThat(supportsItems).as(
				"GitSkipSslValidationCredentialsProvider must support the types supported by its delegate CredentialsProvider")
				.isTrue();
	}

}