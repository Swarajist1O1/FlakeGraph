class DummyClass_104117 {
	@Test
	public void healthIsCached() {
		PropertySource<?> source = new MapPropertySource("foo",
				Collections.<String, Object>emptyMap());
		doReturn(source).when(this.locator).locate(any(Environment.class));

		// not cached
		assertThat(this.indicator.health().getStatus()).isEqualTo(Status.UP);

		// cached
		assertThat(this.indicator.health().getStatus()).isEqualTo(Status.UP);

		verify(this.locator, times(1)).locate(any(Environment.class));
	}

}