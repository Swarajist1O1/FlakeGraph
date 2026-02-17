class DummyClass_113897 {
	@Test
	public void removeByQueryOptions() { // 8 - options
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class,
				() -> template.removeByQuery(Airport.class).withConsistency(QueryScanConsistency.REQUEST_PLUS)
						.inScope(otherScope).inCollection(otherCollection).withOptions(options)
						.matching(Query.query(QueryCriteria.where("iata").is(vie.getIata()))).all().collectList().block());
	}

}