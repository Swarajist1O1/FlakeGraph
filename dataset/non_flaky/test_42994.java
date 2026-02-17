class DummyClass_42994 {
	@Test
	public void testTemplate() throws JsonGenerationException, JsonMappingException, IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Set<Class<?>> classes = Sets.<Class<?>>newHashSet(StackOverflowTemplateSelf.class, StackOverflowTemplateObjectOne.class, StackOverflowTemplateObjectTwo.class);
		
		StackOverflowTemplateSelf objectSelf = new StackOverflowTemplateSelf();
		Map<String, Object> template = JSONDocTemplateBuilder.build(objectSelf.getClass(), classes);
		System.out.println(mapper.writeValueAsString(template));
		
		StackOverflowTemplateObjectOne objectOne = new StackOverflowTemplateObjectOne();
		template = JSONDocTemplateBuilder.build(objectOne.getClass(), classes);
		System.out.println(mapper.writeValueAsString(template));
		
		StackOverflowTemplateObjectTwo objectTwo = new StackOverflowTemplateObjectTwo();
		template = JSONDocTemplateBuilder.build(objectTwo.getClass(), classes);
		System.out.println(mapper.writeValueAsString(template));
	}

}