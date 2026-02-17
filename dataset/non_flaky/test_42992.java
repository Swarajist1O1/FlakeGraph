class DummyClass_42992 {
	@Test
	public void thatTemplateIsMappedToStringCorrectly() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		Set<Class<?>> classes = Sets.<Class<?>>newHashSet(Unordered.class, Ordered.class);

		Map<String, Object> unorderedTemplate = JSONDocTemplateBuilder.build(Unordered.class, classes);
		Assert.assertEquals("{\"aField\":\"\",\"xField\":\"\"}", mapper.writeValueAsString(unorderedTemplate));

		Map<String, Object> orderedTemplate = JSONDocTemplateBuilder.build(Ordered.class, classes);
		Assert.assertEquals("{\"xField\":\"\",\"aField\":\"\",\"bField\":\"\"}", mapper.writeValueAsString(orderedTemplate));
	}

}