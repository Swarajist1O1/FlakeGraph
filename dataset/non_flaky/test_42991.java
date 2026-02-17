class DummyClass_42991 {
	@Test
	public void testTemplate() throws IOException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		ObjectMapper mapper = new ObjectMapper();
		Set<Class<?>> classes = Sets.<Class<?>>newHashSet(MyEnum.class);
		
		Map<String, Object> template = JSONDocTemplateBuilder.build(MyEnum.class, classes);
		System.out.println(mapper.writeValueAsString(template));
	}

}