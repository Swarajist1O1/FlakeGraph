class DummyClass_113866 {
	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		couchbaseTemplate.removeByQuery(User.class).all();
		couchbaseTemplate.removeByQuery(UserAnnotated.class).all();
		couchbaseTemplate.removeByQuery(UserAnnotated2.class).all();
		couchbaseTemplate.removeByQuery(UserAnnotated3.class).all();
		couchbaseTemplate.removeByQuery(User.class).withConsistency(QueryScanConsistency.REQUEST_PLUS).all();
	}

}