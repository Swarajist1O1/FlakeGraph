class DummyClass_113864 {
	@Test
	public void flatMapVsConcatMapCB2() throws Exception {
		System.out.println("Start flatMapCB2");
		System.out.println("\n******** Using flatMap() *********");
		ParallelFlux<GetResult> flat = Flux.fromIterable(keyList).parallel(1).runOn(Schedulers.parallel())
				.flatMap(item -> rCollection.get(item).doOnSubscribe((x) -> System.out.println(" +" + rCat.incrementAndGet()))
						.doOnTerminate(() -> System.out.println(" -" + rCat.getAndDecrement())));
		System.out.println(flat.sequential().collectList().block());
		System.out.println("Start concatMapCB");
		System.out.println("\n******** Using concatMap() *********");
		ParallelFlux<GetResult> concat = Flux.fromIterable(keyList).parallel(2).runOn(Schedulers.parallel())
				.concatMap(item -> cbGet(item).doOnSubscribe((x) -> System.out.println(" +" + rCat.incrementAndGet()))
						.doOnTerminate(() -> System.out.println(" -" + rCat.getAndDecrement())));
		System.out.println(concat.sequential().collectList().block());
		;
	}

}