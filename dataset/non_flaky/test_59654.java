class DummyClass_59654 {
	@Test
	public void aopTest() {
		Animal cat = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
		String result = cat.eat();
		Assert.assertEquals("ç«åé±¼", result);
		cat.seize();
	}

}