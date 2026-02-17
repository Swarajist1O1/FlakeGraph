class DummyClass_42999 {
	@Test
	public void testInvisible() {
		JSONDoc jsonDoc = jsondocScanner.getJSONDoc("version", "basePath", Lists.newArrayList("org.jsondoc.springmvc.issues.invisible"), true, MethodDisplay.URI);
		Assert.assertEquals(1, jsonDoc.getObjects().keySet().size());
		for (String string : jsonDoc.getObjects().keySet()) {
			Assert.assertEquals(2, jsonDoc.getObjects().get(string).size());
		}
		for (ApiDoc apiDoc : jsonDoc.getApis().get("")) {
			for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
				Assert.assertEquals("Resource Interface", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
		}
		
	}

}