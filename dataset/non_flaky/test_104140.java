class DummyClass_104140 {
	@Test
	public void testIfSpacePresentAsUriCreds() {
		this.locator.setUri(new String[] { "http://  @localhost:9999" });
		this.locator.setPassword("secret");
		Credentials credentials = this.locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://localhost:9999");
		assertThat(credentials.getUsername()).isEqualTo("  ");
		assertThat(credentials.getPassword()).isEqualTo("secret");
	}

}