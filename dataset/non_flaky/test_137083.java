class DummyClass_137083 {
	@Test
	public void isFinalWhenFinalReturnsTrue() {
		assertThat(get(TestFinalClass.class).isFinal()).isTrue();
	}

}