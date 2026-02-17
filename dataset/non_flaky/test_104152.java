class DummyClass_104152 {
	@Test
	public void publicKey() {
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));
		String key = this.controller.getPublicKey();
		assertThat(key.startsWith("ssh-rsa")).as("Wrong key format: " + key).isTrue();
	}

}