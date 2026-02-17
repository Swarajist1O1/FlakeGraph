class DummyClass_110931 {
    @Test
    public void affectsTagsVarargShouldRewrite() {
        InsertQuery insertQuery = InsertQuery.builder()
                .table("table")
                .affectsTags("first_call_vararg")
                .affectsTags("second_call_vararg")
                .build();

        assertThat(insertQuery.affectsTags()).isEqualTo(singleton("second_call_vararg"));
    }

}