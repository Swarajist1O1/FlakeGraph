class DummyClass_113877 {
	@Test
	public void removeByQuery() { // 8
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("lowe")).block();
		List<RemoveResult> removeResults = template.removeByQuery(Airport.class)
				.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).matching(Query.query(QueryCriteria.where("iata").is(vie.getIata()))).all().collectList()
				.block();
		assertEquals(saved.getId(), removeResults.get(0).getId());
	}

}