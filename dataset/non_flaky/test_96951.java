class DummyClass_96951 {
  @Test
  public void testCompareUtf8ToString() {
    assertEquals(0, mComparator.compare(new Utf8(""), ""));
    assertThat(mComparator.compare(new Utf8(""), "a"), lessThan(0));
    assertThat(mComparator.compare(new Utf8("a"), ""), greaterThan(0));

    assertEquals(0, mComparator.compare(new Utf8("a"), "a"));
    assertThat(mComparator.compare(new Utf8("a"), "b"), lessThan(0));
    assertThat(mComparator.compare(new Utf8("b"), "a"), greaterThan(0));

    assertEquals(0, mComparator.compare(new Utf8("ab"), "ab"));
    assertThat(mComparator.compare(new Utf8("a"), "aa"), lessThan(0));
    assertThat(mComparator.compare(new Utf8("aa"), "a"), greaterThan(0));

    assertThat(mComparator.compare(new Utf8("abc"), "abcdef"), lessThan(0));
    assertThat(mComparator.compare(new Utf8("abcdef"), "abc"), greaterThan(0));
  }

}