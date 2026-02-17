class DummyClass_42986 {
	@Test
	public void testApiGlobalDoc() {
		ApiGlobalDoc apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Sets.<Class<?>>newHashSet(Global.class), Sets.<Class<?>>newHashSet(), Sets.<Class<?>>newHashSet());
		Assert.assertNotNull(apiGlobalDoc);
		Assert.assertEquals(1, apiGlobalDoc.getSections().size());
		ApiGlobalSectionDoc sectionDoc = apiGlobalDoc.getSections().iterator().next();
		Assert.assertEquals("title", sectionDoc.getTitle());
		Assert.assertEquals(3, sectionDoc.getParagraphs().size());
		
		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Sets.<Class<?>>newHashSet(), Sets.<Class<?>>newHashSet(Changelog.class), Sets.<Class<?>>newHashSet());
		Assert.assertNotNull(apiGlobalDoc);
		Assert.assertEquals(1, apiGlobalDoc.getChangelogset().getChangelogs().size());

		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Sets.<Class<?>>newHashSet(MultipleGlobalSections.class), Sets.<Class<?>>newHashSet(), Sets.<Class<?>>newHashSet());
		Assert.assertNotNull(apiGlobalDoc);
		Assert.assertEquals(3, apiGlobalDoc.getSections().size());
		
		ApiGlobalSectionDoc[] apiGlobalSectionDocs = apiGlobalDoc.getSections().toArray(new ApiGlobalSectionDoc[apiGlobalDoc.getSections().size()]);
		Assert.assertEquals("section1", apiGlobalSectionDocs[0].getTitle());
		Assert.assertEquals("abc", apiGlobalSectionDocs[1].getTitle());
		Assert.assertEquals("198xyz", apiGlobalSectionDocs[2].getTitle());
		
		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Sets.<Class<?>>newHashSet(), Sets.<Class<?>>newHashSet(), Sets.<Class<?>>newHashSet(Migration.class));
		Assert.assertNotNull(apiGlobalDoc);
		Assert.assertEquals(1, apiGlobalDoc.getMigrationset().getMigrations().size());
		
		apiGlobalDoc = jsondocScanner.getApiGlobalDoc(Sets.<Class<?>>newHashSet(AllTogether.class), Sets.<Class<?>>newHashSet(AllTogether.class), Sets.<Class<?>>newHashSet(AllTogether.class));
		Assert.assertNotNull(apiGlobalDoc);
		Assert.assertEquals(1, apiGlobalDoc.getSections().size());
		Assert.assertEquals(1, apiGlobalDoc.getMigrationset().getMigrations().size());
		Assert.assertEquals(1, apiGlobalDoc.getChangelogset().getChangelogs().size());
	}

}