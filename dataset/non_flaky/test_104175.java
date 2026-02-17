class DummyClass_104175 {
	@Test
	public void shouldEncryptUsingApplicationAndProfiles() {

		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(Encryptors.text("application", "11")));

		// when
		String encrypted = this.controller.encrypt(this.application, this.profiles,
				this.data, TEXT_PLAIN);

		// then
		assertThat(this.controller.decrypt(this.application, this.profiles, encrypted,
				TEXT_PLAIN)).isEqualTo(this.data);
	}

}