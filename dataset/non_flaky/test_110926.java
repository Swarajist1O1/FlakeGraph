class DummyClass_110926 {
    @Test
    public void completeBuilderShouldNotAllowNullTable() {
        try {
            //noinspection ConstantConditions
            InsertQuery.builder()
                    .table("test_table")
                    .table(null);
            failBecauseExceptionWasNotThrown(NullPointerException.class);
        } catch (NullPointerException expected) {
            assertThat(expected)
                    .hasMessage("Table name is null or empty")
                    .hasNoCause();
        }
    }

}