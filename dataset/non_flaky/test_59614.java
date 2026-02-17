class DummyClass_59614 {
	@Test
	public void getBeanTest(){
		final Demo2 testDemo = SpringUtil.getBean("testDemo");
		Assert.assertEquals(12345, testDemo.getId());
		Assert.assertEquals("test", testDemo.getName());
	}

}