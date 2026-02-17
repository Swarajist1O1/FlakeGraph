class DummyClass_96950 {
  @Test
  public void testCompareUtf8() {
    assertEquals(0, mComparator.compare(new Utf8(""), new Utf8("")));
    assertThat(mComparator.compare(new Utf8(""), new Utf8("a")), lessThan(0));
    assertThat(mComparator.compare(new Utf8("a"), new Utf8("")), greaterThan(0));

    assertEquals(0, mComparator.compare(new Utf8("a"), new Utf8("a")));
    assertThat(mComparator.compare(new Utf8("a"), new Utf8("b")), lessThan(0));
    assertThat(mComparator.compare(new Utf8("b"), new Utf8("a")), greaterThan(0));

    assertEquals(0, mComparator.compare(new Utf8("ab"), new Utf8("ab")));
    assertThat(mComparator.compare(new Utf8("a"), new Utf8("aa")), lessThan(0));
    assertThat(mComparator.compare(new Utf8("aa"), new Utf8("a")), greaterThan(0));

    assertThat(mComparator.compare(new Utf8("abc"), new Utf8("abcdef")), lessThan(0));
    assertThat(mComparator.compare(new Utf8("abcdef"), new Utf8("abc")), greaterThan(0));
  }

}