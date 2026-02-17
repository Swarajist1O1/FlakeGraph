class DummyClass_77515 {
    @Test
    public void ruleCanRoundtrip() {
        final Long id = daoTestRule.inTransaction(() -> persist(new TestEntity("description")).getId());

        final TestEntity testEntity = get(id);

        assertThat(testEntity).isNotNull();
        assertThat(testEntity.getDescription()).isEqualTo("description");
    }

}