class DummyClass_104133 {
	@Test
	public void vanilla() {
		this.locator.setUri(new String[] { "http://localhost:9999" });
		this.locator.setPassword("secret");
		Credentials credentials = this.locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://localhost:9999");
		assertThat(credentials.getUsername()).isEqualTo("user");
		assertThat(credentials.getPassword()).isEqualTo("secret");
	}

}