class DummyClass_113862 {
	@Test
	public void flatMapCB() throws Exception {
		System.out.println("Start flatMapCB");
		ParallelFlux<GetResult> concat = Flux.fromIterable(keyList).parallel(2).runOn(Schedulers.parallel())
				.flatMap(item -> cbGet(item) /* rCollection.get(item) */
						.doOnSubscribe((x) -> System.out.println(" +" + rCat.incrementAndGet()))
						.doOnTerminate(() -> System.out.println(" -" + rCat.decrementAndGet())));
		System.out.println(concat.sequential().collectList().block());
	}

}