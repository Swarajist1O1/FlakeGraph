class DummyClass_42984 {
	@Test
	public void testApiDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestFlow.class);
		
		List<ApiMethodDoc> apiMethodDocs = new ArrayList<ApiMethodDoc>();
		ApiMethodDoc apiMethodDoc = new ApiMethodDoc();
		apiMethodDoc.setId("F1");
		apiMethodDocs.add(apiMethodDoc);
		
		Set<ApiFlowDoc> apiFlowDocs = jsondocScanner.getApiFlowDocs(classes, apiMethodDocs);
		for (ApiFlowDoc apiFlowDoc : apiFlowDocs) {
			if(apiFlowDoc.getName().equals("flow")) {
				Assert.assertEquals("A test flow", apiFlowDoc.getDescription());
				Assert.assertEquals(3, apiFlowDoc.getSteps().size());
				Assert.assertEquals("F1", apiFlowDoc.getSteps().get(0).getApimethodid());
				Assert.assertEquals("F2", apiFlowDoc.getSteps().get(1).getApimethodid());
				Assert.assertEquals("Flows A", apiFlowDoc.getGroup());
				Assert.assertNotNull(apiFlowDoc.getSteps().get(0).getApimethoddoc());
				Assert.assertEquals("F1", apiFlowDoc.getSteps().get(0).getApimethoddoc().getId());
			}
			
			if(apiFlowDoc.getName().equals("flow2")) {
				Assert.assertEquals("A test flow 2", apiFlowDoc.getDescription());
				Assert.assertEquals(3, apiFlowDoc.getSteps().size());
				Assert.assertEquals("F4", apiFlowDoc.getSteps().get(0).getApimethodid());
				Assert.assertEquals("F5", apiFlowDoc.getSteps().get(1).getApimethodid());
				Assert.assertEquals("Flows B", apiFlowDoc.getGroup());
			}
		}
	}

}