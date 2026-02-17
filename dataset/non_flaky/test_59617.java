class DummyClass_59617 {
	@Test
	public void beanValidatorTest() {
		BeanValidationResult result = ValidationUtil.warpValidate(new TestClass());
		Assert.assertFalse(result.isSuccess());
		Assert.assertEquals(2, result.getErrorMessages().size());
	}

}