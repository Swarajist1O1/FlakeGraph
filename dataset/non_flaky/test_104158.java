class DummyClass_104158 {
	@Test
	public void addEnvironment() {
		TextEncryptorLocator locator = new TextEncryptorLocator() {

			private RsaSecretEncryptor encryptor = new RsaSecretEncryptor();

			@Override
			public TextEncryptor locate(Map<String, String> keys) {
				return this.encryptor;
			}

}