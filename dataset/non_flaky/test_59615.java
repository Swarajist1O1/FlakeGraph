class DummyClass_59615 {
	@Test
	public void getBeanWithTypeReferenceTest() {
		Map<String, Object> mapBean = SpringUtil.getBean(new TypeReference<Map<String, Object>>() {});
		Assert.assertNotNull(mapBean);
		Assert.assertEquals("value1", mapBean.get("key1"));
		Assert.assertEquals("value2", mapBean.get("key2"));
	}

}