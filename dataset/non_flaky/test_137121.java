class DummyClass_137121 {
	@Test
	public void isAbstractWhenNotAbstractReturnsFalse() {
		assertThat(getTagged(WithMethod.class).isAbstract()).isFalse();
	}

}