class DummyClass_137081 {
	@Test
	public void isAnnotationWhenAnnotationReturnsTrue() {
		assertThat(get(TestAnnotation.class).isAnnotation()).isTrue();
	}

}