class DummyClass_137120 {
	@Test
	public void isAbstractWhenAbstractReturnsTrue() {
		assertThat(getTagged(WithAbstractMethod.class).isAbstract()).isTrue();
	}

}