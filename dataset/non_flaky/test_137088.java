class DummyClass_137088 {
	@Test
	public void getEnclosingClassNameWhenHasNoEnclosingClassReturnsNull() {
		assertThat(get(AbstractAnnotationMetadataTests.class).getEnclosingClassName()).isNull();
	}

}