class DummyClass_113908 {
	@Test
	public void insertById() { // 6
		InsertOptions options = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(vie.withId(UUID.randomUUID().toString()));
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(getOptions).one(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}