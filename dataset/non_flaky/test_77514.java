class DummyClass_77514 {
    @Test
    public void ruleCanOpenTransaction() {
        final Long id = daoTestRule.inTransaction(() -> persist(new TestEntity("description")).getId());

        assertThat(id).isNotNull();
    }

}