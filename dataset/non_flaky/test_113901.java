class DummyClass_113901 {
	@BeforeEach
	public void beforeEach() {
		// first call the super method
		super.beforeEach();
		// then do processing for this class
		couchbaseTemplate.removeByQuery(User.class).inCollection(collectionName).all();
		couchbaseTemplate.findByQuery(User.class).withConsistency(QueryScanConsistency.REQUEST_PLUS)
				.inCollection(collectionName).all();
		couchbaseTemplate.removeByQuery(Airport.class).inScope(scopeName).inCollection(collectionName).all();
		couchbaseTemplate.findByQuery(Airport.class).withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(scopeName)
				.inCollection(collectionName).all();
		couchbaseTemplate.removeByQuery(Airport.class).inScope(otherScope).inCollection(otherCollection).all();
		couchbaseTemplate.findByQuery(Airport.class).withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(otherScope)
				.inCollection(otherCollection).all();
	}

}