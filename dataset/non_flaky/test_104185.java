class DummyClass_104185 {
	@Test
	public void testSupportsSslFailureInformationalMessage() {
		CredentialItem informationalMessage = new CredentialItem.InformationalMessage(
				"text " + JGitText.get().sslFailureTrustExplanation + " more text");
		assertThat(this.skipSslValidationCredentialsProvider
				.supports(informationalMessage)).as(
						"GitSkipSslValidationCredentialsProvider should always support SSL failure InformationalMessage")
						.isTrue();

		informationalMessage = new CredentialItem.InformationalMessage("unrelated");
		assertThat(this.skipSslValidationCredentialsProvider
				.supports(informationalMessage)).as(
						"GitSkipSslValidationCredentialsProvider should not support unrelated InformationalMessage items")
						.isFalse();
	}

}