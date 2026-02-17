class DummyClass_94660 {
  @Test
  public void invokeOptionalBadArgs() throws Exception {
    SubClass1 subClass1 = new SubClass1();
    assertIllegalArgumentExceptionOnInvokeOptional(METHOD_WITH_ARGS_CORRECT_PARAMS,
        subClass1); // no args
    assertIllegalArgumentExceptionOnInvokeOptional(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1, 123);
    assertIllegalArgumentExceptionOnInvokeOptional(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1,
        true);
    assertIllegalArgumentExceptionOnInvokeOptional(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1,
        new Object());
    assertIllegalArgumentExceptionOnInvokeOptional(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1,
        "one", "two");
  }

}