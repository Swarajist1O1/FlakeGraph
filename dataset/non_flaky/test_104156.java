class DummyClass_104156 {
	@Test
	public void prefixStrippedBeforeEncrypt() {
		TextEncryptor encryptor = mock(TextEncryptor.class);
		when(encryptor.encrypt(anyString())).thenReturn("myEncryptedValue");

		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(encryptor));
		this.controller.encrypt("{key:test}foo", MediaType.TEXT_PLAIN);

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(encryptor, atLeastOnce()).encrypt(captor.capture());
		assertThat(captor.getValue()).doesNotContain("{key:test}")
				.as("Prefix must be stripped prior to encrypt");
	}

}