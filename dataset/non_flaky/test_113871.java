class DummyClass_113871 {
	@Test
	public void findByAnalytics() { // 2
		AnalyticsOptions options = AnalyticsOptions.analyticsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("low8")).block();
		try {
			List<Airport> found = template.findByAnalytics(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(options).all().collectList().block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}