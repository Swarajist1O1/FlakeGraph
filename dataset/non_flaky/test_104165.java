class DummyClass_104165 {
	@Test
	public void testAddPrefix() {
		assertThat(this.helper.addPrefix(Collections.singletonMap("bar", "spam"), "foo"))
				.isEqualTo("{bar:spam}foo");
	}

}