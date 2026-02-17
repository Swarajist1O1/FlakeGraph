class DummyClass_110924 {
    @Test
    public void shouldNotAllowEmptyTable() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(equalTo("Table name is null or empty"));
        expectedException.expectCause(nullValue(Throwable.class));

        InsertQuery.builder().table("");
    }

}