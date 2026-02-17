class DummyClass_104187 {
	@Test
	public void testSupportsSslValidationYesNoTypes() {
		CredentialItem yesNoType = new CredentialItem.YesNoType(
				JGitText.get().sslTrustNow);
		assertThat(this.skipSslValidationCredentialsProvider.supports(yesNoType)).as(
				"GitSkipSslValidationCredentialsProvider should always support the trust now YesNoType item")
				.isTrue();

		yesNoType = new CredentialItem.YesNoType(
				MessageFormat.format(JGitText.get().sslTrustForRepo, "/a/path.git"));
		assertThat(this.skipSslValidationCredentialsProvider.supports(yesNoType)).as(
				"GitSkipSslValidationCredentialsProvider should always support the trust repo YesNoType item")
				.isTrue();

		yesNoType = new CredentialItem.YesNoType(JGitText.get().sslTrustAlways);
		assertThat(this.skipSslValidationCredentialsProvider.supports(yesNoType)).as(
				"GitSkipSslValidationCredentialsProvider should always support the trust always YesNoType item")
				.isTrue();

		yesNoType = new CredentialItem.YesNoType("unrelated");
		assertThat(this.skipSslValidationCredentialsProvider.supports(yesNoType)).as(
				"GitSkipSslValidationCredentialsProvider should not support unrelated YesNoType items")
				.isFalse();
	}

}