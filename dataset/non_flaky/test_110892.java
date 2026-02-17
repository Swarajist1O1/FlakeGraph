class DummyClass_110892 {
    @Test
    public void putContentValuesIterable() {
        storIOSQLite.put()
                .contentValues(createContentValues(), createContentValues())
                .withPutResolver(createCVPutResolver())
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}