class DummyClass_77516 {
    @Test
    public void transactionThrowsExceptionAsExpected() {
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(()->
            daoTestRule.inTransaction(() -> persist(new TestEntity(null))));
    }

}