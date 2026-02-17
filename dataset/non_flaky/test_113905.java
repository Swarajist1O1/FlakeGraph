class DummyClass_113905 {
	@Test
	public void findById() { // 3
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(options).one(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}