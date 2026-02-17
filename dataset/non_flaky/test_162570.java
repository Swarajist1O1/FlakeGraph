class DummyClass_162570 {
  @Test
  public void shouldIgnoreEmptySpanName() {
    Context contextWithEmptyPath = ServletContextPath.init(Context.root(), p -> p, "");
    Context contextWithPath = ServletContextPath.init(Context.root(), p -> p, "/context");

    assertThat(ServletContextPath.prepend(contextWithEmptyPath, "")).isEqualTo("");
    assertThat(ServletContextPath.prepend(contextWithPath, "")).isEqualTo("/context");

    assertThat(ServletContextPath.prepend(contextWithEmptyPath, null)).isEqualTo(null);
    assertThat(ServletContextPath.prepend(contextWithPath, null)).isEqualTo("/context");
  }

}