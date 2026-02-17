class DummyClass_98277 {
  @Test
  public void testPreventDuplicateBinds() {
    final HostConfig hostConfig = HostConfig.builder()
        .appendBinds("/one:/one")
        .appendBinds("/one:/one")
        .appendBinds("/one:/one")
        .build();

    assertThat(hostConfig.binds(), contains("/one:/one"));
  }

}