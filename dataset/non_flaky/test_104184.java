class DummyClass_104184 {
	@Test
	public void testIsInteractiveWithDelegate() {
		this.skipSslValidationCredentialsProvider = new GitSkipSslValidationCredentialsProvider(
				this.mockDelegateCredentialsProvider);

		when(this.mockDelegateCredentialsProvider.isInteractive()).thenReturn(true);

		assertThat(this.skipSslValidationCredentialsProvider.isInteractive()).as(
				"With a delegate provider, isInteractive value depends on the delegate")
				.isTrue();
	}

}