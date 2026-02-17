class DummyClass_42997 {
	@Test
	public void testTemplateWithConstant() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final Set<Class<?>> classes = Sets.<Class<?>>newHashSet(ClassWithConstant.class);

        final Map<String, Object> template = JSONDocTemplateBuilder.build(ClassWithConstant.class, classes);
        Assert.assertEquals("", template.get("identifier"));
        Assert.assertEquals(null, template.get(THIS_IS_A_CONSTANT));

        final String serializedTemplate =
            "{" +
                "\"identifier\":\"\"" +
            "}";

        assertThat(mapper.writeValueAsString(template), is(serializedTemplate));
	}

}