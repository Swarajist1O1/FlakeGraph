class DummyClass_104170 {
	@Test
	public void testKeysDefaults() {
		Map<String, String> keys = this.helper.getEncryptorKeys("foo", "bar", "spam");
		assertThat(keys.get("name")).isEqualTo("foo");
		assertThat(keys.get("profiles")).isEqualTo("bar");
	}

}