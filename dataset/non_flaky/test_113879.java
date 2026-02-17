class DummyClass_113879 {
	@Test
	public void upsertById() { // 10
		UpsertOptions options = UpsertOptions.upsertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));

		Airport saved = template.upsertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(vie.withIcao("lowf")).block();
		try {
			Airport found = template.findById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(getOptions).one(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}