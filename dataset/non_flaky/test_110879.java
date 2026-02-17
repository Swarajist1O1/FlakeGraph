class DummyClass_110879 {
    @Test
    public void deleteCollectionOfObjects() {
        storIOSQLite.delete()
                .objects(Collections.singleton(createTweet()))
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}