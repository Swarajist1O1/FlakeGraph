class DummyClass_112689 {
	@Test
	public void testException() {
		Throwable cause = new Throwable();
		String msg = "hello";
		SQLException e = SqlExceptionUtil.create(msg, cause);
		assertEquals(msg, e.getMessage());
		assertEquals(cause, e.getCause());
	}

}