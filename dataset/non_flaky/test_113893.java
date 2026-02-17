class DummyClass_113893 {
	@Test
	public void findByQueryOptions() { // 4
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class,
				() -> template.findByQuery(Airport.class).withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(otherScope)
						.inCollection(otherCollection).withOptions(options).all().collectList().block());
	}

}