class DummyClass_104148 {
	@Test(expected = EncryptionTooWeakException.class)
	public void cannotDecryptWithNoopEncryptor() {
		this.controller.decrypt("foo", MediaType.TEXT_PLAIN);
	}

}