class DummyClass_137132 {
	@Test
	public void getAnnotationAttributesReturnsAttributes() {
		assertThat(getTagged(WithAnnotationAttributes.class).getAnnotationAttributes(
				AnnotationAttributes.class.getName())).containsOnly(entry("name", "test"),
						entry("size", 1));
	}

}