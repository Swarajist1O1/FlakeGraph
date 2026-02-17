class DummyClass_110930 {
    @Test
    public void affectsTagsCollectionShouldRewrite() {
        InsertQuery insertQuery = InsertQuery.builder()
                .table("table")
                .affectsTags(new HashSet<String>((singletonList("first_call_collection"))))
                .affectsTags(new HashSet<String>((singletonList("second_call_collection"))))
                .build();

        assertThat(insertQuery.affectsTags()).isEqualTo(singleton("second_call_collection"));
    }

}