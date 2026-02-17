class DummyClass_104157 {
	@Test
	public void encryptDecyptTextWithCurlyBrace() {
		this.controller = new EncryptionController(
				new SingleTextEncryptorLocator(new RsaSecretEncryptor()));

		String plain = "textwith}brace";

}