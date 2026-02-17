class DummyClass_59632 {
	@Test
	public void copyTest() {
		SampleBean bean = new SampleBean();
		bean.setValue("Hello world");

		OtherSampleBean otherBean = new OtherSampleBean();
		CglibUtil.copy(bean, otherBean);
		Assert.assertEquals("Hello world", otherBean.getValue());

		OtherSampleBean otherBean2 = CglibUtil.copy(bean, OtherSampleBean.class);
		Assert.assertEquals("Hello world", otherBean2.getValue());
	}

}