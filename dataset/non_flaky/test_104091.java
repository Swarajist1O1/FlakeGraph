class DummyClass_104091 {
	@Test
	public void contextLoads() {
		// The remote config was bad so there is no bootstrap
		assertThat(this.environment.getPropertySources().contains("bootstrap")).isFalse();
	}

}