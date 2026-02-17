class DummyClass_104167 {
	@Test
	public void testStripNoPrefix() {
		assertThat(this.helper.stripPrefix("foo")).isEqualTo("foo");
	}

}