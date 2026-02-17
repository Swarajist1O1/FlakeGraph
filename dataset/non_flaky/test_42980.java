class DummyClass_42980 {
	@Test
	public void testNoNameApiObjectDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(NoNameApiObject.class);
		ApiObjectDoc apiObjectDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assert.assertEquals("nonameapiobject", apiObjectDoc.getName());
		Assert.assertEquals("id", apiObjectDoc.getFields().iterator().next().getName());
		Assert.assertEquals(1, apiObjectDoc.getJsondochints().size());
	}

}