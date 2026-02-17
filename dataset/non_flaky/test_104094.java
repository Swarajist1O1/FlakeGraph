class DummyClass_104094 {
	@Test
	public void contextFails() {
		try {
			new SpringApplicationBuilder().sources(Application.class).run(
					"--server.port=0", "--spring.cloud.config.enabled=true",
					"--spring.cloud.config.fail-fast=true",
					"--spring.cloud.config.uri=http://server-host-doesnt-exist:1234");
			fail("failFast option did not produce an exception");
		}
		catch (Exception e) {
			assertThat(e.getMessage().contains("fail fast"))
					.as("Exception not caused by fail fast").isTrue();
		}
	}

}