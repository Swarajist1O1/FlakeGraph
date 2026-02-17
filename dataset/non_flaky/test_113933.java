class DummyClass_113933 {
	@Test
	public void testScopeCollectionAnnotation() {
		UserCol user = new UserCol("1", "Dave", "Wilson");
		Query query = Query.query(QueryCriteria.where("firstname").is(user.getFirstname()));
		try {
			UserCol saved = couchbaseTemplate.insertById(UserCol.class).inScope(scopeName).inCollection(collectionName)
					.one(user);
			List<UserCol> found = couchbaseTemplate.findByQuery(UserCol.class)
					.withConsistency(QueryScanConsistency.REQUEST_PLUS).inScope(scopeName).inCollection(collectionName)
					.matching(query).all();
			assertEquals(saved, found.get(0), "should have found what was saved");
			List<UserCol> notfound = couchbaseTemplate.findByQuery(UserCol.class).inScope(CollectionIdentifier.DEFAULT_SCOPE)
					.inCollection(CollectionIdentifier.DEFAULT_COLLECTION).matching(query).all();
			assertEquals(0, notfound.size(), "should not have found what was saved");
			couchbaseTemplate.removeByQuery(UserCol.class).inScope(scopeName).inCollection(collectionName).matching(query)
					.all();
		} finally {
			try {
				couchbaseTemplate.removeByQuery(UserCol.class).inScope(scopeName).inCollection(collectionName).matching(query)
						.all();
			} catch (DataRetrievalFailureException drfe) {}
		}
	}

}