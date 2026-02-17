class DummyClass_59661 {
	@Test
	public void luaTest() throws ScriptException {
		final ScriptEngine engine = ScriptUtil.getLuaEngine();
		engine.eval("print('Hello Lua')");
	}

}