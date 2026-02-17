class DummyClass_59650 {
	@Test
	public void enjoyEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = TemplateUtil.createEngine(
				new TemplateConfig("templates").setCustomEngine(EnjoyEngine.class));
		Template template = engine.getTemplate("#(x + 123)");
		String result = template.render(Dict.create().set("x", 1));
		Assert.assertEquals("124", result);

		//ClassPathæ¨¡æ¿
		engine = new EnjoyEngine(
				new TemplateConfig("templates", ResourceMode.CLASSPATH).setCustomEngine(EnjoyEngine.class));
		template = engine.getTemplate("enjoy_test.etl");
		result = template.render(Dict.create().set("x", 1));
		Assert.assertEquals("124", result);
	}

}