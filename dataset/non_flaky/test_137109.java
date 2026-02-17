class DummyClass_137109 {
	@Test
	public void hasAnnotatedMethodsWhenMatchesDirectAnnotationReturnsTrue() {
		assertThat(get(WithAnnotatedMethod.class).hasAnnotatedMethods(DirectAnnotation1.class.getName())).isTrue();
	}

}