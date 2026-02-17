class DummyClass_113865 {
	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		// already setup by JavaIntegrationTests.beforeAll()
		// ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		// couchbaseTemplate = (CouchbaseTemplate) ac.getBean(COUCHBASE_TEMPLATE);
		// reactiveCouchbaseTemplate = (ReactiveCouchbaseTemplate) ac.getBean(REACTIVE_COUCHBASE_TEMPLATE);
		// ensure each test starts with clean state

		couchbaseTemplate.removeByQuery(User.class).all();
		couchbaseTemplate.findByQuery(User.class).withConsistency(QueryScanConsistency.REQUEST_PLUS).all();
	}

}