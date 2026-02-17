class DummyClass_59647 {
	@Test
	public void rythmEngineTest() {
		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = TemplateUtil.createEngine(
				new TemplateConfig("templates").setCustomEngine(RythmEngine.class));
		Template template = engine.getTemplate("hello,@name");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result);

		// classpathä¸­è·åæ¨¡æ¿
		Template template2 = engine.getTemplate("rythm_test.tmpl");
		String result2 = template2.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("hello,hutool", result2);
	}

}