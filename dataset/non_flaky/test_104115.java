class DummyClass_104115 {
	@Test
	public void testExceptionStatus() {
		doThrow(new IllegalStateException()).when(this.locator)
				.locate(any(Environment.class));
		assertThat(this.indicator.health().getStatus()).isEqualTo(Status.DOWN);
		verify(this.locator, times(1)).locate(any(Environment.class));
	}

}