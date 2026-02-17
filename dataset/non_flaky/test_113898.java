class DummyClass_113898 {
	@Test
	public void replaceByIdOptions() { // 9 - options
		ReplaceOptions options = ReplaceOptions.replaceOptions().timeout(Duration.ofNanos(10));
		assertThrows(AmbiguousTimeoutException.class, () -> template.replaceById(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.withIcao("newIcao")).block());
	}

}