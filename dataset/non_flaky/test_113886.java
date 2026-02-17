class DummyClass_113886 {
	@Test
	public void removeByIdOther() { // 7
		RemoveOptions options = RemoveOptions.removeOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lowm"))
				.block();
		RemoveResult removeResult = template.removeById().inScope(otherScope).inCollection(otherCollection)
				.withOptions(options).one(saved.getId()).block();
		assertEquals(saved.getId(), removeResult.getId());
	}

}