class DummyClass_104180 {
	@Test
	public void testDifferentKeyAndSecret() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "mytestkey");
		map.put("secret", "changeme");
		TextEncryptor encryptor = this.locator.locate(map);
		assertThat(encryptor.decrypt(encryptor.encrypt("foo"))).isEqualTo("foo");
	}

}