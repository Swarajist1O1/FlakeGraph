class DummyClass_113876 {
	@Test
	public void removeById() { // 7
		RemoveOptions options = RemoveOptions.removeOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("lowd")).block();
		RemoveResult removeResult = template.removeById().inScope(scopeName).inCollection(collectionName)
				.withOptions(options).one(saved.getId()).block();
		assertEquals(saved.getId(), removeResult.getId());
	}

}