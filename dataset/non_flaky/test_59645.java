class DummyClass_59645 {
	@Test
	public void createEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿, é»è®¤æ¨¡æ¿å¼æï¼æ­¤å¤ä¸ºBeetl
		TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
		Template template = engine.getTemplate("hello,${name}");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);

		// classpathä¸­è·åæ¨¡æ¿
		engine = TemplateUtil.createEngine(new TemplateConfig("templates", ResourceMode.CLASSPATH));
		Template template2 = engine.getTemplate("beetl_test.btl");
		String result2 = template2.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result2);
	}

}