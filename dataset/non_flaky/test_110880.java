class DummyClass_110880 {
    @Test
    public void deleteObject() {
        storIOSQLite.delete()
                .object(createTweet())
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}