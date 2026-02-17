class DummyClass_104169 {
	@Test
	public void testStripPrefixWithEscape() {
		assertThat(this.helper.stripPrefix("{plain}{key:foo}foo"))
				.isEqualTo("{key:foo}foo");
	}

}