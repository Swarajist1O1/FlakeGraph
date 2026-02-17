class DummyClass_112691 {
	@Test
	public void testConstructor() throws Exception {
		@SuppressWarnings({ "rawtypes" })
		Constructor[] constructors = SqlExceptionUtil.class.getDeclaredConstructors();
		assertEquals(1, constructors.length);
		constructors[0].setAccessible(true);
		constructors[0].newInstance();
	}

}