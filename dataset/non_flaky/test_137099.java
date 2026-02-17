class DummyClass_137099 {
	@Test
	public void getAnnotationAttributesReturnsAttributes() {
		assertThat(get(WithAnnotationAttributes.class).getAnnotationAttributes(AnnotationAttributes.class.getName()))
			.containsOnly(entry("name", "test"), entry("size", 1));
	}

}