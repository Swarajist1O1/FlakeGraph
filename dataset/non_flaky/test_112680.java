class DummyClass_112680 {
	@Test(expected = SQLException.class)
	public void testCreateNoDao() throws Exception {
		One one = new One();
		String stuff = "fewpfjewfew";
		one.stuff = stuff;
		one.create();
	}

}