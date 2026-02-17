class DummyClass_78245 {
  @Test
  public void testSetIsEmpty() throws Exception {

    SetState<String> value = underTest.state(NAMESPACE_1, STRING_SET_ADDR);

    assertThat(value.isEmpty().read(), Matchers.is(true));
    ReadableState<Boolean> readFuture = value.isEmpty();
    value.add("hello");
    assertThat(readFuture.read(), Matchers.is(false));

    value.clear();
    assertThat(readFuture.read(), Matchers.is(true));
  }

}