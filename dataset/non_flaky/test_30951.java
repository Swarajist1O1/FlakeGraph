class DummyClass_30951 {
  @Test
  public void shouldGetSubscriptionHashCode() {
    assertThat(getSubscriptionHashCode(wrapString("a"))).isEqualTo(97);
    assertThat(getSubscriptionHashCode(wrapString("b"))).isEqualTo(98);
    assertThat(getSubscriptionHashCode(wrapString("c"))).isEqualTo(99);
    assertThat(getSubscriptionHashCode(wrapString("foobar"))).isEqualTo(-1268878963);
  }

}