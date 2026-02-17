class DummyClass_38213 {
    @Test
    public void testMetadata() {
        db.putMetadataForTable(TABLE, "yoyo".getBytes());
        final byte[] meta = db.getMetadataForTable(TABLE);
        assertEquals("yoyo", new String(meta));
    }

}