class DummyClass_113894 {
	@Test
	public void findFromReplicasByIdOptions() { // 5
		GetAnyReplicaOptions options = GetAnyReplicaOptions.getAnyReplicaOptions().timeout(Duration.ofNanos(1000));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie)
				.block();
		try {
			Airport found = template.findFromReplicasById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(options).any(saved.getId()).block();
			assertNull(found, "should not have found document in short timeout");
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}