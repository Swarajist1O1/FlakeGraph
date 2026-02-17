class DummyClass_113903 {
	@Test
	public void existsById() { // 1
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		ExistsOptions existsOptions = ExistsOptions.existsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		try {
			Boolean exists = couchbaseTemplate.existsById().inScope(scopeName).inCollection(collectionName)
					.withOptions(existsOptions).one(saved.getId());
			assertTrue(exists, "Airport should exist: " + saved.getId());
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}