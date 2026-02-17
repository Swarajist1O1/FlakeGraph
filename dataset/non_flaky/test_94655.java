class DummyClass_94655 {
  @Test
  public void invoke() throws Exception {
    {
      BaseClass base = new BaseClass();
      assertEquals("string", STRING_METHOD_RETURNS_STRING.invoke(base));
      assertEquals("string", STRING_METHOD_RETURNS_ANY.invoke(base));
      assertErrorOnInvoke(STRING_METHOD_RETURNS_INT, base);
      assertNull(VOID_METHOD_RETURNS_ANY.invoke(base));
      assertNull(VOID_METHOD_RETURNS_VOID.invoke(base));
      assertErrorOnInvoke(SUBCLASS_METHOD_RETURNS_ANY, base);
      assertErrorOnInvoke(SUBCLASS_METHOD_RETURNS_STRING, base);
      assertErrorOnInvoke(SUBCLASS_METHOD_RETURNS_INT, base);
      assertErrorOnInvoke(METHOD_WITH_ARGS_WRONG_PARAMS, base);
      assertErrorOnInvoke(METHOD_WITH_ARGS_CORRECT_PARAMS, base);
    }
    {
      SubClass1 subClass1 = new SubClass1();
      assertEquals("string", STRING_METHOD_RETURNS_STRING.invoke(subClass1));
      assertEquals("string", STRING_METHOD_RETURNS_ANY.invoke(subClass1));
      assertErrorOnInvoke(STRING_METHOD_RETURNS_INT, subClass1);
      assertNull(VOID_METHOD_RETURNS_ANY.invoke(subClass1));
      assertNull(VOID_METHOD_RETURNS_VOID.invoke(subClass1));
      assertEquals("subclassMethod1", SUBCLASS_METHOD_RETURNS_ANY.invoke(subClass1));
      assertEquals("subclassMethod1", SUBCLASS_METHOD_RETURNS_STRING.invoke(subClass1));
      assertErrorOnInvoke(SUBCLASS_METHOD_RETURNS_INT, subClass1);
      assertErrorOnInvoke(METHOD_WITH_ARGS_WRONG_PARAMS, subClass1);
      assertEquals("arg", METHOD_WITH_ARGS_CORRECT_PARAMS.invoke(subClass1, "arg"));
    }

    {
      SubClass2 subClass2 = new SubClass2();
      assertEquals("string", STRING_METHOD_RETURNS_STRING.invoke(subClass2));
      assertEquals("string", STRING_METHOD_RETURNS_ANY.invoke(subClass2));
      assertErrorOnInvoke(STRING_METHOD_RETURNS_INT, subClass2);
      assertNull(VOID_METHOD_RETURNS_ANY.invoke(subClass2));
      assertNull(VOID_METHOD_RETURNS_VOID.invoke(subClass2));
      assertEquals(1234, SUBCLASS_METHOD_RETURNS_ANY.invoke(subClass2));
      assertErrorOnInvoke(SUBCLASS_METHOD_RETURNS_STRING, subClass2);
      assertEquals(1234, SUBCLASS_METHOD_RETURNS_INT.invoke(subClass2));
      assertErrorOnInvoke(METHOD_WITH_ARGS_WRONG_PARAMS, subClass2);
      assertEquals("arg", METHOD_WITH_ARGS_CORRECT_PARAMS.invoke(subClass2, "arg"));
    }
  }

}