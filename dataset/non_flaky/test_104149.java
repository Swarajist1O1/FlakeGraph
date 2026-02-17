class DummyClass_104149 {
	@Test(expected = InvalidCipherException.class)
	public void shouldThrowExceptionOnDecryptInvalidData() {
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));
		this.controller.decrypt("foo", MediaType.TEXT_PLAIN);
	}

}