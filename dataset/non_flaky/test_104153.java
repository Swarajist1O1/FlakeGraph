class DummyClass_104153 {
	@Test
	public void appAndProfile() {
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));
		// Add space to input
		String cipher = this.controller.encrypt("app", "default", "foo bar",
				MediaType.TEXT_PLAIN);
		String decrypt = this.controller.decrypt("app", "default", cipher,
				MediaType.TEXT_PLAIN);
		assertThat(decrypt).as("Wrong decrypted plaintext: " + decrypt)
				.isEqualTo("foo bar");
	}

}