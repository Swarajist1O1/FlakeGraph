class DummyClass_38243 {
    @Test
    public void testTableMetadata() {
        assertEquals(AtlasDbConstants.GENERIC_TABLE_METADATA.length, keyValueService.getMetadataForTable(TEST_TABLE).length);
        keyValueService.putMetadataForTable(TEST_TABLE, ArrayUtils.EMPTY_BYTE_ARRAY);
        assertEquals(0, keyValueService.getMetadataForTable(TEST_TABLE).length);
        keyValueService.putMetadataForTable(TEST_TABLE, metadata0);
        assertTrue(Arrays.equals(metadata0, keyValueService.getMetadataForTable(TEST_TABLE)));
    }

}