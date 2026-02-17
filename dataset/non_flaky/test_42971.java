class DummyClass_42971 {
	@Test
	public void testIssue151() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("", "", Lists.newArrayList("org.jsondoc.core.issues.issue151"), true, MethodDisplay.URI);
		Assert.assertEquals(2, jsonDoc.getObjects().keySet().size());
		Assert.assertEquals(1, jsonDoc.getObjects().get("bargroup").size());
		Assert.assertEquals(1, jsonDoc.getObjects().get("foogroup").size());
	}

}