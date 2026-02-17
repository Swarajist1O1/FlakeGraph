class DummyClass_113885 {
	@Test
	public void insertByIdOther() { // 6
		InsertOptions options = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(vie.withIcao("lowl").withId(UUID.randomUUID().toString())).block();
		try {
			Airport found = template.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(getOptions).one(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}