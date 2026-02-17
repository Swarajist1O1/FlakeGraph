class DummyClass_94659 {
  @Test
  public void invokeOptional() throws Exception {
    {
      BaseClass base = new BaseClass();
      assertEquals("string", STRING_METHOD_RETURNS_STRING.invokeOptional(base));
      assertEquals("string", STRING_METHOD_RETURNS_ANY.invokeOptional(base));
      assertNull(STRING_METHOD_RETURNS_INT.invokeOptional(base));
      assertNull(VOID_METHOD_RETURNS_ANY.invokeOptional(base));
      assertNull(VOID_METHOD_RETURNS_VOID.invokeOptional(base));
      assertNull(SUBCLASS_METHOD_RETURNS_ANY.invokeOptional(base));
      assertNull(SUBCLASS_METHOD_RETURNS_STRING.invokeOptional(base));
      assertNull(SUBCLASS_METHOD_RETURNS_INT.invokeOptional(base));
      assertNull(METHOD_WITH_ARGS_WRONG_PARAMS.invokeOptional(base));
      assertNull(METHOD_WITH_ARGS_CORRECT_PARAMS.invokeOptional(base));
    }
    {
      SubClass1 subClass1 = new SubClass1();
      assertEquals("string", STRING_METHOD_RETURNS_STRING.invokeOptional(subClass1));
      assertEquals("string", STRING_METHOD_RETURNS_ANY.invokeOptional(subClass1));
      assertNull(STRING_METHOD_RETURNS_INT.invokeOptional(subClass1));
      assertNull(VOID_METHOD_RETURNS_ANY.invokeOptional(subClass1));
      assertNull(VOID_METHOD_RETURNS_VOID.invokeOptional(subClass1));
      assertEquals("subclassMethod1", SUBCLASS_METHOD_RETURNS_ANY.invokeOptional(subClass1));
      assertEquals("subclassMethod1", SUBCLASS_METHOD_RETURNS_STRING.invokeOptional(subClass1));
      assertNull(SUBCLASS_METHOD_RETURNS_INT.invokeOptional(subClass1));
      assertNull(METHOD_WITH_ARGS_WRONG_PARAMS.invokeOptional(subClass1));
      assertEquals("arg", METHOD_WITH_ARGS_CORRECT_PARAMS.invokeOptional(subClass1, "arg"));
    }

    {
      SubClass2 subClass2 = new SubClass2();
      assertEquals("string", STRING_METHOD_RETURNS_STRING.invokeOptional(subClass2));
      assertEquals("string", STRING_METHOD_RETURNS_ANY.invokeOptional(subClass2));
      assertNull(STRING_METHOD_RETURNS_INT.invokeOptional(subClass2));
      assertNull(VOID_METHOD_RETURNS_ANY.invokeOptional(subClass2));
      assertNull(VOID_METHOD_RETURNS_VOID.invokeOptional(subClass2));
      assertEquals(1234, SUBCLASS_METHOD_RETURNS_ANY.invokeOptional(subClass2));
      assertNull(SUBCLASS_METHOD_RETURNS_STRING.invokeOptional(subClass2));
      assertEquals(1234, SUBCLASS_METHOD_RETURNS_INT.invokeOptional(subClass2));
      assertNull(METHOD_WITH_ARGS_WRONG_PARAMS.invokeOptional(subClass2));
      assertEquals("arg", METHOD_WITH_ARGS_CORRECT_PARAMS.invokeOptional(subClass2, "arg"));
    }
  }

}