class DummyClass_104178 {
	@Test
	public void testDefaults() {
		TextEncryptor encryptor = this.locator
				.locate(Collections.<String, String>emptyMap());
		assertThat(encryptor.decrypt(encryptor.encrypt("foo"))).isEqualTo("foo");
	}

}