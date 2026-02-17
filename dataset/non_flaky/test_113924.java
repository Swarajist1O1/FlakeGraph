class DummyClass_113924 {
	@Test
	public void findByAnalyticsOptions() { // 2
		AnalyticsOptions options = AnalyticsOptions.analyticsOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> couchbaseTemplate.findByAnalytics(Airport.class)
				.inScope(otherScope).inCollection(otherCollection).withOptions(options).all());
	}

}