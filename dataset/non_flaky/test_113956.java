class DummyClass_113956 {
	@Test
	public Mono<Airport> getPolicyByIdAndEffectiveDateTime(String policyId, Instant effectiveDateTime) {
		return airportRepository
				.findPolicySnapshotByPolicyIdAndEffectiveDateTime(policyId, effectiveDateTime.toEpochMilli())
				// .map(Airport::getEntity)
				.doOnError(
						error -> System.out.println("MSG='Exception happened while retrieving Policy by Id and effectiveDateTime', "
								+ "policyId={}, effectiveDateTime={}"));
	}

}