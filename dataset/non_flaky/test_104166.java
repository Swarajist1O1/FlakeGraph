class DummyClass_104166 {
	@Test
	public void testAddNoPrefix() {
		assertThat(this.helper.addPrefix(Collections.<String, String>emptyMap(), "foo"))
				.isEqualTo("foo");
	}

}