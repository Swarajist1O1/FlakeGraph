class DummyClass_113892 {
	@Test
	public void findByIdOptions() { // 3
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofNanos(10));
		assertThrows(UnambiguousTimeoutException.class, () -> template.findById(Airport.class).inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.getId()).block());
	}

}