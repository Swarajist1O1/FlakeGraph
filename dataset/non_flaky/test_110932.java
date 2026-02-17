class DummyClass_110932 {
    @Test
    public void affectsTagsCollectionAllowsNull() {
        InsertQuery insertQuery = InsertQuery.builder()
                .table("table")
                .affectsTags(new HashSet<String>((singletonList("first_call_collection"))))
                .affectsTags(null)
                .build();

        assertThat(insertQuery.affectsTags()).isEmpty();
    }

}