class DummyClass_137110 {
	@Test
	public void hasAnnotatedMethodsWhenMatchesMetaAnnotationReturnsTrue() {
		assertThat(get(WithMetaAnnotatedMethod.class).hasAnnotatedMethods(MetaAnnotation2.class.getName())).isTrue();
	}

}