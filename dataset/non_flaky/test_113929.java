class DummyClass_113929 {
	@Test
	public void removeByIdOptions() { // 7 - options
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		RemoveOptions options = RemoveOptions.removeOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> couchbaseTemplate.removeById().inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.getId()));

	}

}