class DummyClass_113922 {
	@Test
	public void upsertByIdOther() { // 10
		UpsertOptions options = UpsertOptions.upsertOptions().timeout(Duration.ofSeconds(10));
		GetOptions getOptions = GetOptions.getOptions().timeout(Duration.ofSeconds(10));

		Airport saved = couchbaseTemplate.upsertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(vie);
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(getOptions).one(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}