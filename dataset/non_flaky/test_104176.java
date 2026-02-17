class DummyClass_104176 {
	@Test(expected = EncryptionTooWeakException.class)
	public void shouldNotEncryptUsingNoOp() {
		// given
		String application = "unknown";

		// when
		this.controller.encrypt(application, this.profiles, this.data, TEXT_PLAIN);

		// then exception is thrown
	}

}