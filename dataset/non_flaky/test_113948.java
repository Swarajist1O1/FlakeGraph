class DummyClass_113948 {
	@Test
	public void testScopeCollectionRepoWith() {
		UserCol user = new UserCol("1", "Dave", "Wilson");
		try {
			UserCol saved = userColRepository.withScope(scopeName).withCollection(collectionName).save(user);
			List<UserCol> found = userColRepository.withScope(scopeName).withCollection(collectionName)
					.findByFirstname(user.getFirstname());
			assertEquals(saved, found.get(0), "should have found what was saved");
			List<UserCol> notfound = userColRepository.withScope(DEFAULT_SCOPE)
					.withCollection(CollectionIdentifier.DEFAULT_COLLECTION).findByFirstname(user.getFirstname());
			assertEquals(0, notfound.size(), "should not have found what was saved");
			userColRepository.withScope(scopeName).withCollection(collectionName).delete(user);
		} finally {
			try {
				userColRepository.withScope(scopeName).withCollection(collectionName).delete(user);
			} catch (DataRetrievalFailureException drfe) {}
		}
	}

}