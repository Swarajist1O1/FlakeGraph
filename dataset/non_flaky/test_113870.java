class DummyClass_113870 {
	@Test
	public void existsById() { // 1
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		ExistsOptions existsOptions = ExistsOptions.existsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("low7")).block();
		try {
			Boolean exists = template.existsById().inScope(scopeName).inCollection(collectionName).withOptions(existsOptions)
					.one(saved.getId()).block();
			assertTrue(exists, "Airport should exist: " + saved.getId());
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}