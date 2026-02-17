class DummyClass_104147 {
	@Test(expected = EncryptionTooWeakException.class)
	public void cannotDecryptWithoutKey() {
		this.controller.decrypt("foo", MediaType.TEXT_PLAIN);
	}

}