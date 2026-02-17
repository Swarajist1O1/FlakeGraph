class DummyClass_137124 {
	@Test
	public void isFinalWhenFinalReturnsTrue() {
		assertThat(getTagged(WithFinalMethod.class).isFinal()).isTrue();
	}

}