class DummyClass_113931 {
	@Test
	public void replaceByIdOptions() { // 9 - options
		ReplaceOptions options = ReplaceOptions.replaceOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> couchbaseTemplate.replaceById(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.withIcao("newIcao")));
	}

}