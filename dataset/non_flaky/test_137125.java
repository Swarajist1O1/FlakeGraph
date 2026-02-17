class DummyClass_137125 {
	@Test
	public void isFinalWhenNonFinalReturnsFalse() {
		assertThat(getTagged(WithMethod.class).isFinal()).isFalse();
	}

}