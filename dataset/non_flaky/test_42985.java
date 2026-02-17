class DummyClass_42985 {
	@Test
	public void testApiObjectDocWithHibernateValidator() {
		Set<ApiObjectDoc> apiObjectDocs = jsondocScanner.getApiObjectDocs(Sets.<Class<?>>newHashSet(HibernateValidatorPojo.class));
		Iterator<ApiObjectDoc> iterator = apiObjectDocs.iterator();
		ApiObjectDoc next = iterator.next();
		Set<ApiObjectFieldDoc> fields = next.getFields();
		for (ApiObjectFieldDoc apiObjectFieldDoc : fields) {
			if(apiObjectFieldDoc.getName().equals("id")) {
				Iterator<String> formats = apiObjectFieldDoc.getFormat().iterator();
				Assert.assertEquals("a not empty id", formats.next());
				Assert.assertEquals("length must be between 2 and 2147483647", formats.next());
				Assert.assertEquals("must be less than or equal to 9", formats.next());
			}
		}
	}

}