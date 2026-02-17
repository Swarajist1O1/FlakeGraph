class DummyClass_42978 {
	@Test
	public void testUndefinedVisibilityAndStageDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(UndefinedVisibilityAndStage.class);
		ApiObjectDoc apiObjectDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next();
		Assert.assertEquals("undefinedvisibilityandstage", apiObjectDoc.getName());
		Assert.assertEquals(ApiVisibility.UNDEFINED, apiObjectDoc.getVisibility());
		Assert.assertEquals(ApiStage.UNDEFINED, apiObjectDoc.getStage());
	}

}