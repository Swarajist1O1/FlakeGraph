class DummyClass_104182 {
	@Test
	public void testCanHandle() {
		assertThat(GitSkipSslValidationCredentialsProvider
				.canHandle("https://github.com/org/repo")).as(
						"GitSkipSslValidationCredentialsProvider only handles HTTPS uris")
						.isTrue();
		assertThat(GitSkipSslValidationCredentialsProvider
				.canHandle("git@github.com:org/repo")).as(
						"GitSkipSslValidationCredentialsProvider only handles HTTPS uris")
						.isFalse();
	}

}