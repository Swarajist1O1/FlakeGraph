class DummyClass_104151 {
	@Test
	public void sunnyDayRsaKey() {
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));
		String cipher = this.controller.encrypt("foo", MediaType.TEXT_PLAIN);
		assertThat(this.controller.decrypt(cipher, MediaType.TEXT_PLAIN))
				.isEqualTo("foo");
	}

}