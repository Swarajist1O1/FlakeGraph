class DummyClass_137085 {
	@Test
	public void isIndependentWhenIndependentReturnsTrue() {
		assertThat(get(AbstractAnnotationMetadataTests.class).isIndependent()).isTrue();
		assertThat(get(TestClass.class).isIndependent()).isTrue();
	}

}