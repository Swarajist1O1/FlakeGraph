class DummyClass_162608 {
  @Test
  public void getItemFromCache() throws Exception {
    Cache<Method, String> cache = new MethodCache<>();
    Method key = TestClass.class.getDeclaredMethod("method");
    String value = "Value";

    cache.put(key, value);

    assertThat(cache.get(key)).isEqualTo("Value");
  }

}