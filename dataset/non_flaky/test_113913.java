class DummyClass_113913 {
	@Test
	public void existsByIdOther() { // 1
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		ExistsOptions existsOptions = ExistsOptions.existsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		try {
			Boolean exists = couchbaseTemplate.existsById().inScope(otherScope).inCollection(otherCollection)
					.withOptions(existsOptions).one(saved.getId());
			assertTrue(exists, "Airport should exist: " + saved.getId());
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}