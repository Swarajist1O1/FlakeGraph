class DummyClass_137087 {
	@Test
	public void getEnclosingClassNameWhenHasEnclosingClassReturnsEnclosingClass() {
		assertThat(get(TestClass.class).getEnclosingClassName()).isEqualTo(
				AbstractAnnotationMetadataTests.class.getName());
	}

}