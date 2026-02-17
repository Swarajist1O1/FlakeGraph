class DummyClass_137096 {
	@Test
	public void isAnnotatedWhenMatchesDirectAnnotationReturnsTrue() {
		assertThat(get(WithDirectAnnotations.class).isAnnotated(DirectAnnotation1.class.getName())).isTrue();
	}

}