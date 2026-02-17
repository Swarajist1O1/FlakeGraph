class DummyClass_91507 {
    @Test
    public void executeQuery() throws IOException {
        HttpResponse response = TestUtil.mockHttpResponseWithFile(200, "OK", "query.json");
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(response);
        IRemoteClient.QueryResult queryResult = client.executeQuery("SELECT 1 as val", Collections.emptyList(), new HashMap<String, String>());
        assertEquals(1, queryResult.columnMeta.size());
        Iterable<Object> iterable = queryResult.iterable;
        ArrayList<Object> list = Lists.newArrayList(iterable);
        assertEquals(1, list.size());
    }

}