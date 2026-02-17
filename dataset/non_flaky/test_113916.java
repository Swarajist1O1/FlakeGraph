class DummyClass_113916 {
	@Test
	public void findByQueryOther() { // 4
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		try {
			List<Airport> found = couchbaseTemplate.findByQuery(Airport.class)
					.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(otherScope).inCollection(otherCollection)
					.withOptions(options).all();
			assertEquals(saved.getId(), found.get(0).getId());
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}