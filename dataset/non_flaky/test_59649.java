class DummyClass_59649 {
	@Test
	public void velocityEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = TemplateUtil.createEngine(
				new TemplateConfig("templates", ResourceMode.STRING).setCustomEngine(VelocityEngine.class));
		Template template = engine.getTemplate("ä½ å¥½,$name");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("ä½ å¥½,hutool", result);
		
		//ClassPathæ¨¡æ¿
		engine = TemplateUtil.createEngine(
				new TemplateConfig("templates", ResourceMode.CLASSPATH).setCustomEngine(VelocityEngine.class));
		template = engine.getTemplate("velocity_test.vtl");
		result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("ä½ å¥½,hutool", result);

		template = engine.getTemplate("templates/velocity_test.vtl");
		result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("ä½ å¥½,hutool", result);
	}

}