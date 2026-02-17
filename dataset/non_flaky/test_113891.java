class DummyClass_113891 {
	@Test
	public void findByAnalyticsOptions() { // 2
		AnalyticsOptions options = AnalyticsOptions.analyticsOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> template.findByAnalytics(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).all().collectList().block());
	}

}