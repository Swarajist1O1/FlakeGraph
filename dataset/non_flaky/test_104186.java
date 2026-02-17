class DummyClass_104186 {
	@Test
	public void testSupportsSslFailureInformationalMessageWithDelegate() {
		this.skipSslValidationCredentialsProvider = new GitSkipSslValidationCredentialsProvider(
				this.mockDelegateCredentialsProvider);

		testSupportsSslFailureInformationalMessage();
	}

}