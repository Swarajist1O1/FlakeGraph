class DummyClass_104159 {
	@Test
	public void shouldDecryptEnvironment() {
		// given
		String secret = randomUUID().toString();

		// when
		Environment environment = new Environment("name", "profile", "label");
		environment.add(new PropertySource("a", Collections.<Object, Object>singletonMap(
				environment.getName(), "{cipher}" + this.textEncryptor.encrypt(secret))));

		// then
		assertThat(this.encryptor.decrypt(environment).getPropertySources().get(0)
				.getSource().get(environment.getName())).isEqualTo(secret);
	}

}