class DummyClass_112673 {
	@Test
	public void testCheckCoreVersusAndroidVersionsGood() {
		VersionUtils.setThrownOnErrors(true);
		VersionUtils.checkCoreVersusAndroidVersions(VersionUtils.getCoreVersion());
	}

}