class DummyClass_91505 {
    @Test
    public void retrieveMetaData() throws IOException {
        HttpResponse response = TestUtil.mockHttpResponseWithFile(200, "OK", "tables_and_columns.json");
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(response);

        KylinMeta.KMetaProject metaData = client.retrieveMetaData(connInfo.getProject());

        assertEquals(connInfo.getProject(), metaData.projectName);
        assertTrue(!metaData.catalogs.isEmpty());
        KylinMeta.KMetaCatalog catalog = metaData.catalogs.get(0);
        assertEquals("defaultCatalog", catalog.getName());
        assertEquals(1, catalog.schemas.size());
        KylinMeta.KMetaSchema schema = catalog.schemas.get(0);
        assertEquals("DEFAULT", schema.getName());
        assertEquals(5, schema.tables.size());
    }

}