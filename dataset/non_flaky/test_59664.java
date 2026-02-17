class DummyClass_59664 {
	@Test
	public void getCurrentPidTest() {
		long pid = SystemUtil.getCurrentPID();
		Assert.assertTrue(pid > 0);
	}

}