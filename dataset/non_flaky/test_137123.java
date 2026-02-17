class DummyClass_137123 {
	@Test
	public void isStaticWhenNotStaticReturnsFalse() {
		assertThat(getTagged(WithMethod.class).isStatic()).isFalse();
	}

}