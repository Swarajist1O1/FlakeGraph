class DummyClass_30972 {
  @Test
  public void shouldIterateOverModifiedArray() {
    // given
    final POJOArray pojo = new POJOArray();
    final ValueArray<MinimalPOJO> array = pojo.simpleArray();

    // when
    array.add().setLongProp(123L);

    // then
    final Iterator<MinimalPOJO> iterator = array.iterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getLongProp()).isEqualTo(123L);
    assertThat(iterator.hasNext()).isFalse();
  }

}