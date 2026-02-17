class DummyClass_113918 {
	@Test
	public void insertByIdOther() { // 6
		InsertOptions options = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(vie.withId(UUID.randomUUID().toString()));
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(getOptions).one(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}