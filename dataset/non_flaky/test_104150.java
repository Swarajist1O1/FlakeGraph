class DummyClass_104150 {
	@Test(expected = InvalidCipherException.class)
	public void shouldThrowExceptionOnDecryptWrongKey() {
		RsaSecretEncryptor encryptor = new RsaSecretEncryptor();
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));
		this.controller.decrypt(encryptor.encrypt("foo"), MediaType.TEXT_PLAIN);
	}

}