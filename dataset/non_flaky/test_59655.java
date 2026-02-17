class DummyClass_59655 {
	@Test
	public void aopByAutoCglibTest() {
		Dog dog = ProxyUtil.proxy(new Dog(), TimeIntervalAspect.class);
		String result = dog.eat();
		Assert.assertEquals("çåè", result);

		dog.seize();
	}

}