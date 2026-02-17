class DummyClass_113859 {
	@Test
	public void concatMapCB() throws Exception {
		System.out.println("Start concatMapCB");
		System.out.println("\n******** Using concatMap() *********");
		ParallelFlux<GetResult> concat = Flux.fromIterable(keyList).parallel(2).runOn(Schedulers.parallel())
				.concatMap(item -> cbGet(item)
						/* rCollection.get(item) */.doOnSubscribe((x) -> System.out.println(" +" + rCat.incrementAndGet()))
						.doOnTerminate(() -> System.out.println(" -" + rCat.decrementAndGet())));
		System.out.println(concat.sequential().collectList().block());
	}

}