class DummyClass_113914 {
	@Test
	public void findByAnalyticsOther() { // 2
		AnalyticsOptions options = AnalyticsOptions.analyticsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		try {
			List<Airport> found = couchbaseTemplate.findByAnalytics(Airport.class).inScope(otherScope)
					.inCollection(otherCollection).withOptions(options).all();
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}