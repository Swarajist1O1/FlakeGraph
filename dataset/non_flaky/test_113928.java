class DummyClass_113928 {
	@Test
	public void insertByIdOptions() { // 6
		InsertOptions options = InsertOptions.insertOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> couchbaseTemplate.insertById(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.withId(UUID.randomUUID().toString())));
	}

}