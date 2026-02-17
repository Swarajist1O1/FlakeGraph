class DummyClass_77517 {
    @Test
    public void rollsBackTransaction() {
        // given a successfully persisted entity
        final TestEntity testEntity = new TestEntity("description");
        daoTestRule.inTransaction(() -> persist(testEntity));

        // when we prepare an update of that entity
        testEntity.setDescription("newDescription");
        // ... but cause a constraint violation during the actual update
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> daoTestRule.inTransaction(() -> {
                persist(testEntity);
                persist(new TestEntity(null));
            }));
        // ... the entity has the original value
        assertThat(get(testEntity.getId()).getDescription()).isEqualTo("description");
    }

}