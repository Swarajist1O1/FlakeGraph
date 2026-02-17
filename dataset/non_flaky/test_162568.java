class DummyClass_162568 {
  @Test
  public void shouldAddSlashBetweenContextAndSpanName() {
    Context contextWithEmptyPath = ServletContextPath.init(Context.root(), p -> p, "");
    Context contextWithPath = ServletContextPath.init(Context.root(), p -> p, "/context");

    assertThat(ServletContextPath.prepend(contextWithEmptyPath, "spanName")).isEqualTo("spanName");
    assertThat(ServletContextPath.prepend(contextWithPath, "spanName"))
        .isEqualTo("/context/spanName");
  }

}