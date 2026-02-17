class DummyClass_113880 {
	@Test
	public void existsByIdOther() { // 1
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		ExistsOptions existsOptions = ExistsOptions.existsOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lowg"))
				.block();
		try {
			Boolean exists = template.existsById().inScope(otherScope).inCollection(otherCollection)
					.withOptions(existsOptions).one(saved.getId()).block();
			assertTrue(exists, "Airport should exist: " + saved.getId());
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}