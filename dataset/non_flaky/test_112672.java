class DummyClass_112672 {
	@Test(expected = IllegalStateException.class)
	public void testCheckCoreVersusJdbcVersionsBad() {
		VersionUtils.setThrownOnErrors(true);
		VersionUtils.checkCoreVersusJdbcVersions("xxx");
	}

}