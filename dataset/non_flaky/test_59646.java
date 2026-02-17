class DummyClass_59646 {
	@Test
	public void beetlEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = new BeetlEngine(new TemplateConfig("templates"));
		Template template = engine.getTemplate("hello,${name}");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);

		// classpathä¸­è·åæ¨¡æ¿
		engine = new BeetlEngine(new TemplateConfig("templates", ResourceMode.CLASSPATH));
		Template template2 = engine.getTemplate("beetl_test.btl");
		String result2 = template2.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result2);
	}

}