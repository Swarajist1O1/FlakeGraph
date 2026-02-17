class DummyClass_113878 {
	@Test
	public void replaceById() { // 9
		InsertOptions insertOptions = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		ReplaceOptions options = ReplaceOptions.replaceOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(insertOptions).one(vie.withIcao("lowe")).block();
		Airport replaced = template.replaceById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(vie.withIcao("newIcao")).block();
		try {
			Airport found = template.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(getOptions).one(saved.getId()).block();
			assertEquals(replaced, found);
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}