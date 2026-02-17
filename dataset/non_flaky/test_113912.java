class DummyClass_113912 {
	@Test
	public void upsertById() { // 10
		UpsertOptions options = UpsertOptions.upsertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));

		Airport saved = couchbaseTemplate.upsertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(vie);
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(getOptions).one(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}