class DummyClass_38192 {
    @Test
    public void testGetFullTableNameLegacy() {
        MatcherAssert.assertThat(
                Schemas.getFullTableName(TABLE_NAME, Namespace.create("met")),
                Matchers.equalTo(TABLE_NAME)
        );
    }

}