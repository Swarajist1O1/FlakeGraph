class DummyClass_97696 {
    @Test
    public void testAsyncResultWithGenerics() {
        final Settings settings = TestUtils.settings();
        settings.outputKind = TypeScriptOutputKind.module;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(AsyncUsage.class));
        Assert.assertTrue(output.contains("result: AsyncOperationResultUnion<string>"));
        Assert.assertTrue(output.contains("type AsyncOperationResultUnion<T> = InProgressResult<T> | FinishedResult<T> | FailedResult<T>"));
    }

}