class DummyClass_113872 {
	@Test
	public void findById() { // 3
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("low9")).block();
		try {
			Airport found = template.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(options).one(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}