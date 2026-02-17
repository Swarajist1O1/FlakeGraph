class DummyClass_110891 {
    @Test
    public void putContentValues() {
        storIOSQLite.put()
                .contentValues(createContentValues())
                .withPutResolver(createCVPutResolver())
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}