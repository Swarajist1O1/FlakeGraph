class DummyClass_104142 {
	@Test
	public void testThatExplicitUsernamePasswordTakePrecedence() {
		ConfigClientProperties properties = new ConfigClientProperties(
				new MockEnvironment());

		properties.setUri(
				new String[] { "https://userInfoName:userInfoPW@localhost:8888/" });
		properties.setUsername("explicitName");
		properties.setPassword("explicitPW");
		Credentials credentials = properties.getCredentials(0);
		assertThat(credentials.getPassword()).isEqualTo("explicitPW");
		assertThat(credentials.getUsername()).isEqualTo("explicitName");
	}

}