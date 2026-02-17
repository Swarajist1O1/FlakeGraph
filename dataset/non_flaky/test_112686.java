class DummyClass_112686 {
	@Test(expected = IllegalArgumentException.class)
	public void testObjectEqualsNoDao() {
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		one.objectToString();
	}

}