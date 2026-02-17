class DummyClass_110923 {
    @Test
    public void shouldNotAllowNullTable() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(equalTo("Table name is null or empty"));
        expectedException.expectCause(nullValue(Throwable.class));

        //noinspection ConstantConditions
        InsertQuery.builder().table(null);
    }

}