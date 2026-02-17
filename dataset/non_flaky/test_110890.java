class DummyClass_110890 {
    @Test
    public void putCollection() {
        storIOSQLite.put()
                .objects(Collections.singleton(createTweet()))
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}