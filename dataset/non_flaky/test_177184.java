class DummyClass_177184 {
    @Test
    public void get_returnsNullWhenNoCurrentRequestContext() {
        assertThat(currentTraceContext.get()).isNull();
    }

}