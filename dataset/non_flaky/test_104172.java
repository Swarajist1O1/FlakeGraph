class DummyClass_104172 {
	@Test
	public void testKeysWithPrefixAndEscape() {
		Map<String, String> keys = this.helper.getEncryptorKeys("foo", "bar",
				"{key:mykey}{plain}{foo:bar}foo");
		assertThat(keys.size()).isEqualTo(3);
		assertThat(keys.get("key")).isEqualTo("mykey");
	}

}