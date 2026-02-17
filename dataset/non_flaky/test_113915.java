class DummyClass_113915 {
	@Test
	public void findByIdOther() { // 3
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		try {
			Airport found = couchbaseTemplate.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(options).one(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}