class DummyClass_110161 {
	@Test
	public void testToString() throws IOException {
		assertEquals(Paths.get(System.getProperty("user.dir")).toString(),
				dm.toString());
	}

}