class DummyClass_77513 {
    @Test
    public void ruleCreatedSessionFactory() {
        final SessionFactory sessionFactory = daoTestRule.getSessionFactory();

        assertThat(sessionFactory).isNotNull();
    }

}