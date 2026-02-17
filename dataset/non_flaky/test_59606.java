class DummyClass_59606 {
	@Test
	public void evalTest(){
		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = ExpressionUtil.eval("a-(b-c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

}