class DummyClass_104171 {
	@Test
	public void testKeysWithPrefix() {
		Map<String, String> keys = this.helper.getEncryptorKeys("foo", "bar",
				"{key:mykey}foo");
		assertThat(keys.size()).isEqualTo(3);
		assertThat(keys.get("key")).isEqualTo("mykey");
	}

}