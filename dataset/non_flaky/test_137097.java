class DummyClass_137097 {
	@Test
	public void isAnnotatedWhenMatchesMetaAnnotationReturnsTrue() {
		assertThat(get(WithMetaAnnotations.class).isAnnotated(MetaAnnotation2.class.getName())).isTrue();
	}

}