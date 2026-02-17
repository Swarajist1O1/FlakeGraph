class DummyClass_104177 {
	@Test(expected = EncryptionTooWeakException.class)
	public void shouldNotDecryptUsingNoOp() {
		// given
		String application = "unknown";

		// when
		this.controller.decrypt(application, this.profiles, this.data, TEXT_PLAIN);

		// then exception is thrown
	}

}