class DummyClass_113881 {
	@Test
	public void findByAnalyticsOther() { // 2
		AnalyticsOptions options = AnalyticsOptions.analyticsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lowh"))
				.block();
		try {
			List<Airport> found = template.findByAnalytics(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(options).all().collectList().block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}