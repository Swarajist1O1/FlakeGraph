class DummyClass_104116 {
	@Test
	public void testServerUp() {
		PropertySource<?> source = new MapPropertySource("foo",
				Collections.<String, Object>emptyMap());
		doReturn(source).when(this.locator).locate(any(Environment.class));
		assertThat(this.indicator.health().getStatus()).isEqualTo(Status.UP);
		verify(this.locator, times(1)).locate(any(Environment.class));
	}

}