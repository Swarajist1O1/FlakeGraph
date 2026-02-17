class DummyClass_113932 {
	@Test
	public void upsertByIdOptions() { // 10 - options
		UpsertOptions options = UpsertOptions.upsertOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> couchbaseTemplate.upsertById(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie));
	}

}