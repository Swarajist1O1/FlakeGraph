class DummyClass_113952 {
	@Test
	public void testScopeCollectionAnnotation() {
		// template default scope is my_scope
		// UserCol annotation scope is other_scope
		UserCol user = new UserCol("1", "Dave", "Wilson");
		try {
			UserCol saved = userColRepository.withCollection(otherCollection).save(user).block(); // should use UserCol
																																														// annotation
			// scope
			List<UserCol> found = userColRepository.withCollection(otherCollection).findByFirstname(user.getFirstname())
					.collectList().block();
			assertEquals(saved, found.get(0), "should have found what was saved");
			List<UserCol> notfound = userColRepository.withScope(CollectionIdentifier.DEFAULT_SCOPE)
					.withCollection(CollectionIdentifier.DEFAULT_COLLECTION).findByFirstname(user.getFirstname()).collectList()
					.block();
			assertEquals(0, notfound.size(), "should not have found what was saved");
		} finally {
			try {
				userColRepository.withScope(otherScope).withCollection(otherCollection).delete(user);
			} catch (DataRetrievalFailureException drfe) {}
		}
	}

}