class DummyClass_77511 {
    @Test
    public void ruleCreatedSessionFactory() {
        final SessionFactory sessionFactory = daoTestRule.getSessionFactory();

        assertThat(sessionFactory).isNotNull();
    }

}