class DummyClass_113951 {
	@Test
	public void myTest() {

		ReactiveAirportRepository ar = airportRepository.withScope(scopeName).withCollection(collectionName);
		Airport vie = new Airport("airports::vie", "vie", "loww");
		try {
			Airport saved = ar.save(vie).block();
			Airport airport2 = ar.save(saved).block();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			ar.delete(vie).block();
		}

	}

}