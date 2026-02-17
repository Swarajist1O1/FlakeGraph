class DummyClass_113953 {
	@Test
	public void testScopeCollectionRepoWith() {
		UserCol user = new UserCol("1", "Dave", "Wilson");
		try {
			UserCol saved = userColRepository.withScope(scopeName).withCollection(collectionName).save(user).block();
			List<UserCol> found = userColRepository.withScope(scopeName).withCollection(collectionName)
					.findByFirstname(user.getFirstname()).collectList().block();
			assertEquals(saved, found.get(0), "should have found what was saved");
			List<UserCol> notfound = userColRepository.withScope(CollectionIdentifier.DEFAULT_SCOPE)
					.withCollection(CollectionIdentifier.DEFAULT_COLLECTION).findByFirstname(user.getFirstname()).collectList()
					.block();
			assertEquals(0, notfound.size(), "should not have found what was saved");
			userColRepository.withScope(scopeName).withCollection(collectionName).delete(user).block();
		} finally {
			try {
				userColRepository.withScope(scopeName).withCollection(collectionName).delete(user).block();
			} catch (DataRetrievalFailureException drfe) {}
		}
	}

}