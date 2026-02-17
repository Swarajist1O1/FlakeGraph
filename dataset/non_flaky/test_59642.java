class DummyClass_59642 {
	@Test
	public void charsetTest(){
		final TemplateConfig config = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
		config.setCustomEngine(VelocityEngine.class);
		config.setCharset(CharsetUtil.CHARSET_GBK);
		final TemplateEngine engine = TemplateUtil.createEngine(config);
		Template template = engine.getTemplate("velocity_test_gbk.vtl");
		String result = template.render(Dict.create().set("name", "hutool"));
		Assert.assertEquals("ä½ å¥½,hutool", result);
	}

}