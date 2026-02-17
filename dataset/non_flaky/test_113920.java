class DummyClass_113920 {
	@Test
	public void removeByQueryOther() { // 8
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		List<RemoveResult> removeResults = couchbaseTemplate.removeByQuery(Airport.class)
				.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).matching(Query.query(QueryCriteria.where("iata").is(vie.getIata()))).all();
		assertEquals(saved.getId(), removeResults.get(0).getId());
	}

}