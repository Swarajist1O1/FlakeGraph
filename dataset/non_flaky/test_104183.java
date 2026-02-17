class DummyClass_104183 {
	@Test
	public void testIsInteractive() {
		assertThat(this.skipSslValidationCredentialsProvider.isInteractive()).as(
				"GitSkipSslValidationCredentialsProvider with no delegate requires no user interaction")
				.isFalse();
	}

}