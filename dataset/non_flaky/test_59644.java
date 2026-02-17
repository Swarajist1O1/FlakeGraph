class DummyClass_59644 {
	@Test
	public void thymeleafEngineTest2() {
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

		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("list", list);

		 hutoolApi(map);
		thymeleaf(map);
	}

}