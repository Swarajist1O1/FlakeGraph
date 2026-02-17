class DummyClass_137082 {
	@Test
	public void isAnnotationWhenNotAnnotationReturnsFalse() {
		assertThat(get(TestClass.class).isAnnotation()).isFalse();
		assertThat(get(TestInterface.class).isAnnotation()).isFalse();
	}

}