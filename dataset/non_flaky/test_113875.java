class DummyClass_113875 {
	@Test
	public void insertById() { // 6
		InsertOptions options = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(vie.withIcao("lowc").withId(UUID.randomUUID().toString())).block();
		try {
			Airport found = template.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(getOptions).one(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}