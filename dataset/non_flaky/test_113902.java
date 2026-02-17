class DummyClass_113902 {
	@AfterEach
	public void afterEach() {
		// first do processing for this class
		couchbaseTemplate.removeByQuery(User.class).inCollection(collectionName).all();
		// query with REQUEST_PLUS to ensure that the remove has completed.
		couchbaseTemplate.findByQuery(User.class).withConsistency(QueryScanConsistency.REQUEST_PLUS)
				.inCollection(collectionName).all();
		// then call the super method
		super.afterEach();
	}

}