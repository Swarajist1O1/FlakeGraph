class DummyClass_59653 {
	@Test
	public void renderStrTest() throws IOException {
		GroupTemplate groupTemplate = BeetlUtil.createGroupTemplate(new StringTemplateResourceLoader(), Configuration.defaultConfiguration());
		Template template = BeetlUtil.getTemplate(groupTemplate, "hello,${name}");
		String result = BeetlUtil.render(template, Dict.create().set("name", "hutool"));

		Assert.assertEquals("hello,hutool", result);

		String renderFromStr = BeetlUtil.renderFromStr("hello,${name}", Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", renderFromStr);

	}

}