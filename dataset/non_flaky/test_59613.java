class DummyClass_59613 {
	@Test
	public void registerBeanTest() {
		Demo2 registerBean = new Demo2();
		registerBean.setId(123);
		registerBean.setName("222");
		SpringUtil.registerBean("registerBean", registerBean);

		Demo2 registerBean2 = SpringUtil.getBean("registerBean");
		Assert.assertEquals(123, registerBean2.getId());
		Assert.assertEquals("222", registerBean2.getName());
	}

}