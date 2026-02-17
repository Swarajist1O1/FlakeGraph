class DummyClass_113887 {
	@Test
	public void removeByQueryOther() { // 8
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lown"))
				.block();
		List<RemoveResult> removeResults = template.removeByQuery(Airport.class)
				.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).matching(Query.query(QueryCriteria.where("iata").is(vie.getIata()))).all().collectList()
				.block();
		assertEquals(saved.getId(), removeResults.get(0).getId());
	}

}