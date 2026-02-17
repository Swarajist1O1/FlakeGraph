class DummyClass_137133 {
	@Test
	public void getAllAnnotationAttributesReturnsAllAttributes() {
		MultiValueMap<String, Object> attributes = getTagged(
				WithMetaAnnotationAttributes.class).getAllAnnotationAttributes(
						AnnotationAttributes.class.getName());
		assertThat(attributes).containsOnlyKeys("name", "size");
		assertThat(attributes.get("name")).containsExactlyInAnyOrder("m1", "m2");
		assertThat(attributes.get("size")).containsExactlyInAnyOrder(1, 2);
	}

}