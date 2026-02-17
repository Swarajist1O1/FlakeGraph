class DummyClass_113921 {
	@Test
	public void replaceByIdOther() { // 9
		InsertOptions insertOptions = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		ReplaceOptions options = ReplaceOptions.replaceOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(insertOptions).one(vie);
		Airport replaced = couchbaseTemplate.replaceById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(vie.withIcao("newIcao"));
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(getOptions).one(saved.getId());
			assertEquals(replaced, found);
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}