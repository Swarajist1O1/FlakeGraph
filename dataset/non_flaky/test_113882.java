class DummyClass_113882 {
	@Test
	public void findByIdOther() { // 3
		GetOptions options = GetOptions.getOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection).one(vie.withIcao("lowi"))
				.block();
		try {
			Airport found = template.findById(Airport.class).inScope(otherScope).inCollection(otherCollection)
					.withOptions(options).one(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId()).block();
		}
	}

}