class DummyClass_59612 {
	@Test
	public void simpleTest(){
		Foo foo = new Foo(100, 3.14f, new Date());
		ExpressionEngine engine = new AviatorEngine();
		String exp =
				"\"[foo i=\"+ foo.i + \", f=\" + foo.f + \", date.year=\" + (foo.date.year+1900) + \", date.month=\" + foo.date.month + \", bars[0].name=\" + #foo.bars[0].name + \"]\"";
		String result = (String) engine.eval(exp, Dict.create().set("foo", foo));
		Assert.assertEquals("[foo i=100, f=3.14, date.year=2020, date.month=10, bars[0].name=bar]", result);

		// Assignment.
		exp = "#foo.bars[0].name='hello aviator' ; #foo.bars[0].name";
		result = (String) engine.eval(exp, Dict.create().set("foo", foo));
		Assert.assertEquals("hello aviator", result);
		Assert.assertEquals("hello aviator", foo.bars[0].getName());

		exp = "foo.bars[0] = nil ; foo.bars[0]";
		result = (String) engine.eval(exp, Dict.create().set("foo", foo));
		Console.log("Execute expression: " + exp);
		Assert.assertNull(result);
		Assert.assertNull(foo.bars[0]);
	}

}