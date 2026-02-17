class DummyClass_43071 {
    @Test
    public void testRollback()
    {
        skipTestUnless(hasBehavior(SUPPORTS_MULTI_STATEMENT_WRITES));

        String table = "test_rollback_" + randomTableSuffix();
        computeActual(format("CREATE TABLE %s (x int)", table));

        assertThatThrownBy(() ->
                inTransaction(session -> {
                    assertUpdate(session, format("INSERT INTO %s VALUES (42)", table), 1);
                    throw new RollbackException();
                }))
                .isInstanceOf(RollbackException.class);

        assertQuery(format("SELECT count(*) FROM %s", table), "SELECT 0");
    }

}