class DummyClass_113896 {
	@Test
	public void removeByIdOptions() { // 7 - options
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie)
				.block();
		RemoveOptions options = RemoveOptions.removeOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> template.removeById().inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.getId()).block());

	}

}