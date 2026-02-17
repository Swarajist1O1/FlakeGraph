class DummyClass_91510 {
  @Test
  public void testConvertBooleanType() {
      assertEquals("java.lang.Boolean", KylinClient.convertType(Types.BOOLEAN).getName());
  }

}