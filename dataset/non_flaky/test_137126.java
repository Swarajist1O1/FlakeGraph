class DummyClass_137126 {
	@Test
	public void isOverridableWhenOverridableReturnsTrue() {
		assertThat(getTagged(WithMethod.class).isOverridable()).isTrue();
	}

}