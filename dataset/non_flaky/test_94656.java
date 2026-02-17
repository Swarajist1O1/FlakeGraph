class DummyClass_94656 {
  @Test
  public void invokeBadArgs() throws Exception {
    SubClass1 subClass1 = new SubClass1();
    assertIllegalArgumentExceptionOnInvoke(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1); // no args
    assertIllegalArgumentExceptionOnInvoke(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1, 123);
    assertIllegalArgumentExceptionOnInvoke(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1, true);
    assertIllegalArgumentExceptionOnInvoke(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1,
        new Object());
    assertIllegalArgumentExceptionOnInvoke(METHOD_WITH_ARGS_CORRECT_PARAMS, subClass1, "one",
        "two");
  }

}