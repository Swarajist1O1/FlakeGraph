class DummyClass_42979 {
	@Test
	public void testTemplateApiObjectDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TemplateApiObject.class);
		ApiObjectDoc apiObjectDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assert.assertEquals("templateapiobject", apiObjectDoc.getName());
		Iterator<ApiObjectFieldDoc> iterator = apiObjectDoc.getFields().iterator();
		Assert.assertEquals("id", iterator.next().getName());
		Assert.assertEquals("name", iterator.next().getName());
	}

}