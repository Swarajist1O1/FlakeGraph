class DummyClass_113927 {
	@Test
	public void findFromReplicasByIdOptions() { // 5
		GetAnyReplicaOptions options = GetAnyReplicaOptions.getAnyReplicaOptions().timeout(Duration.ofNanos(1000));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		try {
			Airport found = couchbaseTemplate.findFromReplicasById(Airport.class).inScope(otherScope)
					.inCollection(otherCollection).withOptions(options).any(saved.getId());
			assertNull(found, "should not have found document in short timeout");
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}