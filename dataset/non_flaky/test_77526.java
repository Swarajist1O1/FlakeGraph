class DummyClass_77526 {
    @Test
    public void explicitConfigCreatesSessionFactory() {
        // it yields a valid SessionFactory instance
        final SessionFactory sessionFactory = database.getSessionFactory();
        assertThat(sessionFactory).isNotNull();
        assertThat(sessionFactory.getProperties())
                .containsEntry(AvailableSettings.FORMAT_SQL, "true")
                .containsEntry("foobar", "baz");

        final Session currentSession = sessionFactory.getCurrentSession();

        // an instance of an entity contained in the package can be saved
        currentSession.saveOrUpdate(new TestEntity("foo"));
    }

}