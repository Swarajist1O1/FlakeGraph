class DummyClass_104134 {
	@Test
	public void uriCreds() {
		this.locator.setUri(new String[] { "http://foo:bar@localhost:9999" });
		Credentials credentials = this.locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://localhost:9999");
		assertThat(credentials.getUsername()).isEqualTo("foo");
		assertThat(credentials.getPassword()).isEqualTo("bar");
	}

}