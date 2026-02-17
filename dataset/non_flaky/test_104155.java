class DummyClass_104155 {
	@Test
	public void formDataInWithPrefix() {
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));
		// Add space to input
		String cipher = this.controller.encrypt("{key:test}foo bar=",
				MediaType.APPLICATION_FORM_URLENCODED);
		String decrypt = this.controller.decrypt(cipher + "=",
				MediaType.APPLICATION_FORM_URLENCODED);
		assertThat(decrypt).as("Wrong decrypted plaintext: " + decrypt)
				.isEqualTo("foo bar");
	}

}