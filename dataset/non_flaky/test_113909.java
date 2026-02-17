class DummyClass_113909 {
	@Test
	public void removeById() { // 7
		RemoveOptions options = RemoveOptions.removeOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		RemoveResult removeResult = couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(saved.getId());
		assertEquals(saved.getId(), removeResult.getId());
	}

}