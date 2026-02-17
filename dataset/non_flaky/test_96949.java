class DummyClass_96949 {
  @Test
  public void testCompareString() {
    assertEquals(0, mComparator.compare("", ""));
    assertThat(mComparator.compare("", "a"), lessThan(0));
    assertThat(mComparator.compare("a", ""), greaterThan(0));

    assertEquals(0, mComparator.compare("a", "a"));
    assertThat(mComparator.compare("a", "b"), lessThan(0));
    assertThat(mComparator.compare("b", "a"), greaterThan(0));

    assertEquals(0, mComparator.compare("ab", "ab"));
    assertThat(mComparator.compare("a", "aa"), lessThan(0));
    assertThat(mComparator.compare("aa", "a"), greaterThan(0));

    assertThat(mComparator.compare("abc", "abcdef"), lessThan(0));
    assertThat(mComparator.compare("abcdef", "abc"), greaterThan(0));
  }

}