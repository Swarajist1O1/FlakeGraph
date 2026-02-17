class DummyClass_38191 {
    @Test
    public void testGetFullTableName() {
        MatcherAssert.assertThat(
                Schemas.getFullTableName(TABLE_NAME, NAMESPACE),
                Matchers.equalTo(NAMESPACE.getName() + "." + TABLE_NAME));
    }

}