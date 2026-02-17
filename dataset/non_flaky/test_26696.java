class DummyClass_26696 {
	@Test
	public void testParseDevOpsCompany() {
		PairingBoard pairingBoard = new PairingBoard(null, null, null);
		
		assertThat(pairingBoard.parseDevOpsCompanies("devops:company"), is(new String[] {"company"}));
		assertThat(pairingBoard.parseDevOpsCompanies("devops:company,companyb"), is(new String[] {"company", "companyb"}));
		assertThat(pairingBoard.parseDevOpsCompanies("devops:"), is(new String[] {}));
	}

}