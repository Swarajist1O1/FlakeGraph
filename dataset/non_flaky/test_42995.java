class DummyClass_42995 {
	@Test
	public void typeOneTwo() throws JsonGenerationException, JsonMappingException, IOException {
		Set<Class<?>> classes = Sets.<Class<?>>newHashSet(NotAnnotatedStackOverflowObjectOne.class, NotAnnotatedStackOverflowObjectTwo.class);
		
		NotAnnotatedStackOverflowObjectOne typeOne = new NotAnnotatedStackOverflowObjectOne();
		Map<String, Object> template = JSONDocTemplateBuilder.build(typeOne.getClass(), classes);
		System.out.println(mapper.writeValueAsString(template));
	}

}