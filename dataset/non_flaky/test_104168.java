class DummyClass_104168 {
	@Test
	public void testStripPrefix() {
		assertThat(this.helper.stripPrefix("{key:foo}foo")).isEqualTo("foo");
	}

}