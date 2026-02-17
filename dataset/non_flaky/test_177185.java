class DummyClass_177185 {
    @Test
    public void get_returnsNullWhenCurrentRequestContext_hasNoTraceAttribute() {
        try (SafeCloseable requestContextScope = ctx.push()) {
            assertThat(currentTraceContext.get()).isNull();
        }
    }

}