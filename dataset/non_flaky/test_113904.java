class DummyClass_113904 {
	@Test
	public void findByAnalytics() { // 2
		AnalyticsOptions options = AnalyticsOptions.analyticsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		try {
			List<Airport> found = couchbaseTemplate.findByAnalytics(Airport.class).inScope(scopeName)
					.inCollection(collectionName).withOptions(options).all();
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}