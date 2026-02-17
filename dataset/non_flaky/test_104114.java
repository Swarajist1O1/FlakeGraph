class DummyClass_104114 {
	@Test
	public void testDefaultStatus() {
		// UNKNOWN is better than DOWN since it doesn't stop the app from working
		assertThat(this.indicator.health().getStatus()).isEqualTo(Status.UNKNOWN);
	}

}