class DummyClass_42981 {
	@Test
	public void testEnumObjectDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestEnum.class);
		ApiObjectDoc childDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next(); 
		Assert.assertEquals("test-enum", childDoc.getName());
		Assert.assertEquals(0, childDoc.getFields().size());
		Assert.assertEquals(TestEnum.TESTENUM1.name(), childDoc.getAllowedvalues()[0]);
		Assert.assertEquals(TestEnum.TESTENUM2.name(), childDoc.getAllowedvalues()[1]);
		Assert.assertEquals(TestEnum.TESTENUM3.name(), childDoc.getAllowedvalues()[2]);
	}

}