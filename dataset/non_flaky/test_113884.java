class DummyClass_113884 {
	@Test
	public void findFromReplicasByIdOther() { // 5
		GetAnyReplicaOptions options = GetAnyReplicaOptions.getAnyReplicaOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lowk"))
				.block();
		try {
			Airport found = template.findFromReplicasById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(options).any(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}