class DummyClass_104188 {
	@Test
	public void testSupportsYesNoTypeWithDelegate() {
		this.skipSslValidationCredentialsProvider = new GitSkipSslValidationCredentialsProvider(
				this.mockDelegateCredentialsProvider);

		testSupportsSslValidationYesNoTypes();
	}

}