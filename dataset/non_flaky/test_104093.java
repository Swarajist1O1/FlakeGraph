class DummyClass_104093 {
	@Test
	public void contextLoads() {
		Map res = new TestRestTemplate().getForObject(
				"http://localhost:" + this.port + BASE_PATH + "/env/info.foo", Map.class);
		assertThat(res).containsKey("propertySources");
		Map<String, Object> property = (Map<String, Object>) res.get("property");
		assertThat(property).containsEntry("value", "bar");
	}

}