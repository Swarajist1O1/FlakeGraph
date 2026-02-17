class DummyClass_59643 {
	@Test
	public void thymeleafEngineTest() {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "a");

		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "b");

		// æ¥ææµè¯
		Map<String, Object> map3 = new HashMap<>();
		map3.put("name", DateUtil.parse("2019-01-01"));

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		list.add(map3);

		// å­ç¬¦ä¸²æ¨¡æ¿
		TemplateEngine engine = new ThymeleafEngine(new TemplateConfig());
		Template template = engine.getTemplate("<h3 th:each=\"item : ${list}\" th:text=\"${item.name}\"></h3>");
		String render = template.render(Dict.create().set("list", list));
		Assert.assertEquals("<h3>a</h3><h3>b</h3><h3>2019-01-01 00:00:00</h3>", render);
	}

}