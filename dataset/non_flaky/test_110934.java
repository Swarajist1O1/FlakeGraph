class DummyClass_110934 {
    @Test
    public void shouldNotAllowNullTag() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(startsWith("affectsTag must not be null or empty, affectsTags = "));
        expectedException.expectCause(nullValue(Throwable.class));

        //noinspection ConstantConditions
        InsertQuery.builder()
                .table("table")
                .affectsTags((String) null)
                .build();
    }

}