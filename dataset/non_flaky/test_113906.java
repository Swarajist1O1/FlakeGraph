class DummyClass_113906 {
	@Test
	public void findByQuery() { // 4
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		try {
			List<Airport> found = couchbaseTemplate.findByQuery(Airport.class)
					.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(scopeName).inCollection(collectionName)
					.withOptions(options).all();
			assertEquals(saved.getId(), found.get(0).getId());
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}