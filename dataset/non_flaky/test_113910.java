class DummyClass_113910 {
	@Test
	public void removeByQuery() { // 8
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		List<RemoveResult> removeResults = couchbaseTemplate.removeByQuery(Airport.class)
				.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).matching(Query.query(QueryCriteria.where("iata").is(vie.getIata()))).all();
		assertEquals(saved.getId(), removeResults.get(0).getId());
	}

}