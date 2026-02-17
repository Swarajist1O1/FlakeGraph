class DummyClass_110935 {
    @Test
    public void shouldNotAllowEmptyTag() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(startsWith("affectsTag must not be null or empty, affectsTags = "));
        expectedException.expectCause(nullValue(Throwable.class));

        //noinspection ConstantConditions
        InsertQuery.builder()
                .table("table")
                .affectsTags("")
                .build();
    }

}