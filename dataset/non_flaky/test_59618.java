class DummyClass_59618 {
	@Test
	public void propertyValidatorTest() {
		BeanValidationResult result = ValidationUtil.warpValidateProperty(new TestClass(), "name");
		Assert.assertFalse(result.isSuccess());
		Assert.assertEquals(1, result.getErrorMessages().size());
	}

}