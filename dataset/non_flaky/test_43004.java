class DummyClass_43004 {
	@Test
	public void testApiVerb() {
		ApiObjectDoc buildObject = SpringObjectBuilder.buildObject(MyObject.class);
		Assert.assertEquals("MyObject", buildObject.getName());
		Assert.assertEquals(3, buildObject.getFields().size());
	}

}