class DummyClass_98276 {
  @Test
  public void testAppendBinds() {
    final List<String> initialBinds = ImmutableList.of("/one:/one", "/two:/two");
    final HostConfig hostConfig = HostConfig.builder()
        .binds(initialBinds)
        .appendBinds("/three:/three")
        .appendBinds("/four:/four")
        .build();

    final List<String> expected = ImmutableList.<String>builder()
        .addAll(initialBinds)
        .add("/three:/three")
        .add("/four:/four")
        .build();

    assertThat("Calling .appendBinds should append to the list, not replace",
               hostConfig.binds(), is(expected));
  }

}