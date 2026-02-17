class DummyClass_59657 {
	@Test
	public void compileTest() {
		CompiledScript script = ScriptUtil.compile("print('Script test!');");
		try {
			script.eval();
		} catch (ScriptException e) {
			throw new ScriptRuntimeException(e);
		}
	}

}