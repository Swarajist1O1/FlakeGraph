class DummyClass_137129 {
	@Test
	public void isAnnotatedWhenMatchesDirectAnnotationReturnsTrue() {
		assertThat(getTagged(WithDirectAnnotation.class).isAnnotated(
				DirectAnnotation.class.getName())).isTrue();
	}

}