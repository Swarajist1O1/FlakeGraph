class DummyClass_110861 {
    @Test
    public void getNumberOfResults() {
        putUsersBlocking(8);

        Integer numberOfResults = storIOSQLite
                .get()
                .numberOfResults()
                .withQuery(UserTableMeta.QUERY_ALL)
                .prepare()
                .executeAsBlocking();

        assertThat(numberOfResults).isEqualTo(8);
    }

}