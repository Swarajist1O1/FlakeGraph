class DummyClass_137127 {
	@Test
	public void isOverridableWhenNonOverridableReturnsFalse() {
		assertThat(getTagged(WithStaticMethod.class).isOverridable()).isFalse();
		assertThat(getTagged(WithFinalMethod.class).isOverridable()).isFalse();
		assertThat(getTagged(WithPrivateMethod.class).isOverridable()).isFalse();
	}

}