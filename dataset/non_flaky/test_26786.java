class DummyClass_26786 {
	@Test
	public void testIsDevOpsRotationWeeklyFalse() {
		Company company = new Company("Company");

		company.setDevOpsRotationStrategy("");
		assertThat(company.isDevOpsRotationWeekly(), is(false));

		company.setDevOpsRotationStrategy("foo");
		assertThat(company.isDevOpsRotationWeekly(), is(false));
	}

}