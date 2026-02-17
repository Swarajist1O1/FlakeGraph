class DummyClass_110927 {
    @Test
    public void completeBuilderShouldNotAllowEmptyTable() {
        try {
            InsertQuery.builder()
                    .table("test_table")
                    .table("");
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException expected) {
            assertThat(expected)
                    .hasMessage("Table name is null or empty")
                    .hasNoCause();
        }
    }

}