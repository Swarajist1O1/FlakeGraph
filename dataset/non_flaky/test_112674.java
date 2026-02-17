class DummyClass_112674 {
	@Test(expected = IllegalStateException.class)
	public void testCheckCoreVersusAndroidVersionsBad() {
		VersionUtils.setThrownOnErrors(true);
		VersionUtils.checkCoreVersusAndroidVersions("xxx");
	}

}