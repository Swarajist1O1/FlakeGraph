class DummyClass_137103 {
	@Test
	public void hasAnnotationWhenMatchesDirectAnnotationReturnsTrue() {
		assertThat(get(WithDirectAnnotations.class).hasAnnotation(DirectAnnotation1.class.getName())).isTrue();
	}

}