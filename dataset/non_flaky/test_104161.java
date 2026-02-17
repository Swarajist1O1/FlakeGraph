class DummyClass_104161 {
	@Test
	public void shouldBeAbleToUseNullAsPropertyValue() {

		// when
		Environment environment = new Environment("name", "profile", "label");
		environment.add(new PropertySource("a",
				Collections.<Object, Object>singletonMap(environment.getName(), null)));

		// then
		assertThat(this.encryptor.decrypt(environment).getPropertySources().get(0)
				.getSource().get(environment.getName())).isEqualTo(null);
	}

}