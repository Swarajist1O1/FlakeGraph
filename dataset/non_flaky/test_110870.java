class DummyClass_110870 {
    @Test
    public void nestedTransaction() {
        storIOSQLite.lowLevel().beginTransaction();

        storIOSQLite.lowLevel().beginTransaction();

        storIOSQLite.lowLevel().setTransactionSuccessful();
        storIOSQLite.lowLevel().endTransaction();

        storIOSQLite.lowLevel().setTransactionSuccessful();
        storIOSQLite.lowLevel().endTransaction();
    }

}