class DummyClass_113861 {
	@Test
	public void pairIdAndResult() {
		LinkedList<Airport> list = new LinkedList<>();
		Airport a = new Airport(UUID.randomUUID().toString(), "iata", "lowp");
		for (int i = 0; i < 5; i++) {
			list.add(a.withId(UUID.randomUUID().toString()));
		}
		Flux<Object> af = Flux.fromIterable(list).concatMap((entity) -> airportRepository.save(entity));
		List<Object> saved = af.collectList().block();
		System.out.println("results.size() : " + saved.size());
		Flux<Pair<String, Mono<Airport>>> pairFlux = Flux.fromIterable(list)
				.map((airport) -> Pair.of(airport.getId(), airportRepository.findById(airport.getId())));
		List<Pair<String, Mono<Airport>>> airportPairs = pairFlux.collectList().block();
		for (Pair<String, Mono<Airport>> airportPair : airportPairs) {
			System.out.println("id: " + airportPair.getFirst() + " airport: " + airportPair.getSecond().block());
		}

	}

}