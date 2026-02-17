class DummyClass_113889 {
	@Test
	public void upsertByIdOther() { // 10
		UpsertOptions options = UpsertOptions.upsertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));

		Airport saved = template.upsertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(vie.withIcao("lowo")).block();
		try {
			Airport found = template.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(getOptions).one(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}