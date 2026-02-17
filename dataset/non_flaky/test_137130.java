class DummyClass_137130 {
	@Test
	public void isAnnotatedWhenMatchesMetaAnnotationReturnsTrue() {
		assertThat(getTagged(WithMetaAnnotation.class).isAnnotated(
				DirectAnnotation.class.getName())).isTrue();
	}

}