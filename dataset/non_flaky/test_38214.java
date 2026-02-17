class DummyClass_38214 {
    @Test
    public void testCreateTables() {
        db.putMetadataForTable(TABLE, "yoyo".getBytes());
        final byte[] meta = db.getMetadataForTable(TABLE);
        assertEquals("yoyo", new String(meta));
    }

}