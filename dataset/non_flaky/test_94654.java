class DummyClass_94654 {
  @Test
  public void isSupported() throws Exception {
    {
      BaseClass base = new BaseClass();
      assertTrue(STRING_METHOD_RETURNS_ANY.isSupported(base));
      assertTrue(STRING_METHOD_RETURNS_STRING.isSupported(base));
      assertFalse(STRING_METHOD_RETURNS_INT.isSupported(base));
      assertTrue(VOID_METHOD_RETURNS_ANY.isSupported(base));
      assertTrue(VOID_METHOD_RETURNS_VOID.isSupported(base));
      assertFalse(SUBCLASS_METHOD_RETURNS_ANY.isSupported(base));
      assertFalse(SUBCLASS_METHOD_RETURNS_STRING.isSupported(base));
      assertFalse(SUBCLASS_METHOD_RETURNS_INT.isSupported(base));
      assertFalse(METHOD_WITH_ARGS_WRONG_PARAMS.isSupported(base));
      assertFalse(METHOD_WITH_ARGS_CORRECT_PARAMS.isSupported(base));
    }
    {
      SubClass1 subClass1 = new SubClass1();
      assertTrue(STRING_METHOD_RETURNS_ANY.isSupported(subClass1));
      assertTrue(STRING_METHOD_RETURNS_STRING.isSupported(subClass1));
      assertFalse(STRING_METHOD_RETURNS_INT.isSupported(subClass1));
      assertTrue(VOID_METHOD_RETURNS_ANY.isSupported(subClass1));
      assertTrue(VOID_METHOD_RETURNS_VOID.isSupported(subClass1));
      assertTrue(SUBCLASS_METHOD_RETURNS_ANY.isSupported(subClass1));
      assertTrue(SUBCLASS_METHOD_RETURNS_STRING.isSupported(subClass1));
      assertFalse(SUBCLASS_METHOD_RETURNS_INT.isSupported(subClass1));
      assertFalse(METHOD_WITH_ARGS_WRONG_PARAMS.isSupported(subClass1));
      assertTrue(METHOD_WITH_ARGS_CORRECT_PARAMS.isSupported(subClass1));
    }
    {
      SubClass2 subClass2 = new SubClass2();
      assertTrue(STRING_METHOD_RETURNS_ANY.isSupported(subClass2));
      assertTrue(STRING_METHOD_RETURNS_STRING.isSupported(subClass2));
      assertFalse(STRING_METHOD_RETURNS_INT.isSupported(subClass2));
      assertTrue(VOID_METHOD_RETURNS_ANY.isSupported(subClass2));
      assertTrue(VOID_METHOD_RETURNS_VOID.isSupported(subClass2));
      assertTrue(SUBCLASS_METHOD_RETURNS_ANY.isSupported(subClass2));
      assertFalse(SUBCLASS_METHOD_RETURNS_STRING.isSupported(subClass2));
      assertTrue(SUBCLASS_METHOD_RETURNS_INT.isSupported(subClass2));
      assertFalse(METHOD_WITH_ARGS_WRONG_PARAMS.isSupported(subClass2));
      assertTrue(METHOD_WITH_ARGS_CORRECT_PARAMS.isSupported(subClass2));
    }
  }

}