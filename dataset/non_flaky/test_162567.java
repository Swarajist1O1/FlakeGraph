class DummyClass_162567 {
  @Test
  public void shouldSerializeMapAsKeyCollection() {
    // given
    Map<String, Object> map = new HashMap<>();
    map.put("uno", 1L);
    map.put("dos", new LinkedHashMap<>());
    map.put("tres", "cuatro");
    // when
    String serialized = new Serializer().serialize(map);
    // then
    assertThat(serialized).isEqualTo("[uno,dos,tres]");
  }

}