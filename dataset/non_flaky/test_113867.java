class DummyClass_113867 {
	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		List<RemoveResult> r1 = reactiveCouchbaseTemplate.removeByQuery(User.class).all().collectList().block();
		List<RemoveResult> r2 = reactiveCouchbaseTemplate.removeByQuery(UserAnnotated.class).all().collectList().block();
		List<RemoveResult> r3 = reactiveCouchbaseTemplate.removeByQuery(UserAnnotated2.class).all().collectList().block();
	}

}