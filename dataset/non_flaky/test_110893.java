class DummyClass_110893 {
    @Test
    public void putObject() {
        storIOSQLite.put()
                .object(createTweet())
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}