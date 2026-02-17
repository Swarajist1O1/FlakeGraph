class DummyClass_113919 {
	@Test
	public void removeByIdOther() { // 7
		RemoveOptions options = RemoveOptions.removeOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		RemoveResult removeResult = couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(saved.getId());
		assertEquals(saved.getId(), removeResult.getId());
	}

}