class DummyClass_113911 {
	@Test
	public void replaceById() { // 9
		InsertOptions insertOptions = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		ReplaceOptions options = ReplaceOptions.replaceOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(insertOptions).one(vie);
		Airport replaced = couchbaseTemplate.replaceById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(vie.withIcao("newIcao"));
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(getOptions).one(saved.getId());
			assertEquals(replaced, found);
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}