class DummyClass_42998 {
	@Test
	public void testTemplate() throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		ObjectMapper mapper = new ObjectMapper();
		Set<Class<?>> classes = Sets.<Class<?>>newHashSet(NoAnnotationPojo.class);
		
		Map<String, Object> template = JSONDocTemplateBuilder.build(NoAnnotationPojo.class, classes);
		System.out.println(mapper.writeValueAsString(template));
	}

}