class DummyClass_113873 {
	@Test
	public void findByQuery() { // 4
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("lowa")).block();
		try {
			List<Airport> found = template.findByQuery(Airport.class).withConsistency(QueryScanConsistency.REQUEST_PLUS)
					.inScope(scopeName).inCollection(collectionName).withOptions(options).all().collectList().block();
			assertEquals(saved.getId(), found.get(0).getId());
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}