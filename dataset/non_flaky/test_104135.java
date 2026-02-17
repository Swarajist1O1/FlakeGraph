class DummyClass_104135 {
	@Test
	public void explicitPassword() {
		this.locator.setUri(new String[] { "http://foo:bar@localhost:9999" });
		this.locator.setPassword("secret");
		Credentials credentials = this.locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://localhost:9999");
		assertThat(credentials.getUsername()).isEqualTo("foo");
		assertThat(credentials.getPassword()).isEqualTo("secret");
	}

}