class DummyClass_113945 {
	@Test
	public void myTest() {

		AirportRepository ar = airportRepository.withScope(scopeName).withCollection(collectionName);
		Airport vie = new Airport("airports::vie", "vie", "loww");
		try {
			Airport saved = ar.save(vie);
			Airport airport2 = ar.save(saved);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			ar.delete(vie);
		}

	}

}