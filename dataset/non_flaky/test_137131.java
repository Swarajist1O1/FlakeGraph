class DummyClass_137131 {
	@Test
	public void isAnnotatedWhenDoesNotMatchDirectOrMetaAnnotationReturnsFalse() {
		assertThat(getTagged(WithMethod.class).isAnnotated(
				DirectAnnotation.class.getName())).isFalse();
	}

}