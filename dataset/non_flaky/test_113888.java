class DummyClass_113888 {
	@Test
	public void replaceByIdOther() { // 9
		InsertOptions insertOptions = InsertOptions.insertOptions().timeout(Duration.ofSeconds(10));
		ReplaceOptions options = ReplaceOptions.replaceOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(insertOptions).one(vie.withIcao("lown")).block();
		Airport replaced = template.replaceById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(vie.withIcao("newIcao")).block();
		try {
			Airport found = template.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(getOptions).one(saved.getId()).block();
			assertEquals(replaced, found);
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}