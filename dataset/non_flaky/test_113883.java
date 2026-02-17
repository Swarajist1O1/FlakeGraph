class DummyClass_113883 {
	@Test
	public void findByQueryOther() { // 4
		QueryOptions options = QueryOptions.queryOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lowj"))
				.block();
		try {
			List<Airport> found = template.findByQuery(Airport.class).withConsistency(QueryScanConsistency.REQUEST_PLUS)
					.inScope(otherScope).inCollection(otherCollection).withOptions(options).all().collectList().block();
			assertEquals(saved.getId(), found.get(0).getId());
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}