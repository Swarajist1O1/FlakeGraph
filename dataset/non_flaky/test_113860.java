class DummyClass_113860 {
	@Test
	public void cbse() {
		LinkedList<LinkedList<Airport>> listOfLists = new LinkedList<>();
		Airport a = new Airport(UUID.randomUUID().toString(), "iata", "lowp");
		String last = null;
		for (int i = 0; i < 5; i++) {
			LinkedList<Airport> list = new LinkedList<>();
			for (int j = 0; j < 10; j++) {
				list.add(a.withId(UUID.randomUUID().toString()));
				last = a.getId();
			}
			listOfLists.add(list);
		}
		Flux<Object> af = Flux.fromIterable(listOfLists).concatMap(catalogToStore -> Flux.fromIterable(catalogToStore)
				.parallel(4).runOn(Schedulers.parallel()).concatMap((entity) -> airportRepository.save(entity)));
		List<Object> saved = af.collectList().block();
		System.out.println("results.size() : " + saved.size());

		String statement = "select * from `" + /*config().bucketname()*/ "_default" + "` where META().id >= '" + last + "'";
		System.out.println("statement: " + statement);
		try {
			QueryResult qr = couchbaseTemplate.getCouchbaseClientFactory().getScope().query(statement,
					QueryOptions.queryOptions().profile(QueryProfile.PHASES));
			List<RemoveResult> rr = couchbaseTemplate.removeByQuery(Airport.class)
					.withOptions(QueryOptions.queryOptions().scanConsistency(QueryScanConsistency.REQUEST_PLUS)).all();
			System.out.println(qr.metaData().profile().get());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		List<Airport> airports = airportRepository.findAll().collectList().block();
		assertEquals(0, airports.size(), "should have been all deleted");
	}

}