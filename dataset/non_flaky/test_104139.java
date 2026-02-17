class DummyClass_104139 {
	@Test
	public void testIfColonPresentAtTheStartAndEndInUriCreds() {
		this.locator.setUri(new String[] { "http://:foobar:@localhost:9999" });
		Credentials credentials = this.locator.getCredentials(0);
		assertThat(credentials.getUri()).isEqualTo("http://localhost:9999");
		assertThat(credentials.getUsername()).isEqualTo("");
		assertThat(credentials.getPassword()).isEqualTo("foobar:");
	}

}