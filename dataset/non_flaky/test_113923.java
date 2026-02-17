class DummyClass_113923 {
	@Test
	public void existsByIdOptions() { // 1 - Options
		ExistsOptions options = ExistsOptions.existsOptions().timeout(Duration.ofNanos(10));
		assertThrows(UnambiguousTimeoutException.class, () -> couchbaseTemplate.existsById().inScope(otherScope)
				.inCollection(otherCollection).withOptions(options).one(vie.getId()));
	}

}