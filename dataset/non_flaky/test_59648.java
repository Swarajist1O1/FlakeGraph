class DummyClass_59648 {
	@Test
	public void freemarkerEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = TemplateUtil.createEngine(
				new TemplateConfig("templates", ResourceMode.STRING).setCustomEngine(FreemarkerEngine.class));
		Template template = engine.getTemplate("hello,${name}");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);
		
		//ClassPathæ¨¡æ¿
		engine = TemplateUtil.createEngine(
				new TemplateConfig("templates", ResourceMode.CLASSPATH).setCustomEngine(FreemarkerEngine.class));
		template = engine.getTemplate("freemarker_test.ftl");
		result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);
	}

}