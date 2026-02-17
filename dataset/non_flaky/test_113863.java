class DummyClass_113863 {
	@Test
	public void flatMapSyncCB() throws Exception {
		System.out.println("Start flatMapSyncCB");
		System.out.println("\n******** Using flatSyncMap() *********");
		ParallelFlux<GetResult> concat = Flux.fromIterable(keyList).parallel(2).runOn(Schedulers.parallel())
				.flatMap(item -> Flux.just(cbGetSync(item) /* collection.get(item) */));
		System.out.println(concat.sequential().collectList().block());
		;
	}

}