class DummyClass_38193 {
    @Test
    public void testGetFullTableNameEmptyNamespace() {
        MatcherAssert.assertThat(
                Schemas.getFullTableName(TABLE_NAME, Namespace.EMPTY_NAMESPACE),
                Matchers.equalTo(TABLE_NAME)
        );
    }

}