class DummyClass_78239 {
  @Test
  public void testValue() throws Exception {
    ValueState<String> value = underTest.state(NAMESPACE_1, STRING_VALUE_ADDR);

    // State instances are cached, but depend on the namespace.
    assertThat(underTest.state(NAMESPACE_1, STRING_VALUE_ADDR), equalTo(value));
    assertThat(underTest.state(NAMESPACE_2, STRING_VALUE_ADDR), not(equalTo(value)));

    assertThat(value.read(), Matchers.nullValue());
    value.write("hello");
    assertThat(value.read(), equalTo("hello"));
    value.write("world");
    assertThat(value.read(), equalTo("world"));

    value.clear();
    assertThat(value.read(), Matchers.nullValue());
    assertThat(underTest.state(NAMESPACE_1, STRING_VALUE_ADDR), equalTo(value));
  }

}