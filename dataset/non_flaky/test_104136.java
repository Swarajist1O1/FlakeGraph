class DummyClass_104136 {
	@Test
	public void testIfNoColonPresentInUriCreds() {
		this.locator.setUri(new String[] { "http://foobar@localhost:9999" });
		this.locator.setPassword("secret");
		Credentials credentials = this.locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://localhost:9999");
		assertThat(credentials.getUsername()).isEqualTo("foobar");
		assertThat(credentials.getPassword()).isEqualTo("secret");
	}

}