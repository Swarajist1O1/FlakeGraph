class DummyClass_59652 {
	@Test
	public void renderToFileTest() {
		TemplateEngine engine = new BeetlEngine(new TemplateConfig("templates", ResourceMode.CLASSPATH));
		Template template = engine.getTemplate("freemarker_test.ftl");

		final Map<String, Object> bindingMap = new HashMap<>();
		bindingMap.put("name", "aa");
		File outputFile = new File("e:/test.txt");
		template.render(bindingMap, outputFile);
	}

}