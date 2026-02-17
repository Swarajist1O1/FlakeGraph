class DummyClass_59651 {
	@Test
	public void thymeleafEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = TemplateUtil.createEngine(
				new TemplateConfig("templates").setCustomEngine(ThymeleafEngine.class));
		Template template = engine.getTemplate("<h3 th:text=\"${message}\"></h3>");
		String result = template.render(Dict.create().set("message", "Hutool"));
		Assert.assertEquals("<h3>Hutool</h3>", result);
		
		//ClassPathæ¨¡æ¿
		engine = TemplateUtil.createEngine(
				new TemplateConfig("templates", ResourceMode.CLASSPATH).setCustomEngine(ThymeleafEngine.class));
		template = engine.getTemplate("thymeleaf_test.ttl");
		result = template.render(Dict.create().set("message", "Hutool"));
		Assert.assertEquals("<h3>Hutool</h3>", result);
	}

}