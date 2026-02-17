class DummyClass_113899 {
	@Test
	public void upsertByIdOptions() { // 10 - options
		UpsertOptions options = UpsertOptions.upsertOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> template.upsertById(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie).block());
	}

}