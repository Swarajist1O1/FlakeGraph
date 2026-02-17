class DummyClass_98275 {
  @Test
  public void testReplaceBinds() {
    final List<String> initialBinds = ImmutableList.of("/one:/one", "/two:/two");
    final HostConfig hostConfig = HostConfig.builder()
        .binds(initialBinds)
        .binds(initialBinds)
        .build();

    assertThat("Calling .binds() multiple times should replace the list each time",
               hostConfig.binds(), is(initialBinds));
  }

}