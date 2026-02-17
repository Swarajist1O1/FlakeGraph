class DummyClass_112671 {
	@Test
	public void testCheckCoreVersusJdbcVersionsGood() {
		VersionUtils.setThrownOnErrors(true);
		VersionUtils.checkCoreVersusJdbcVersions(VersionUtils.getCoreVersion());
	}

}