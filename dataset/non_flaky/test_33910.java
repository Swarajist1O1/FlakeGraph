class DummyClass_33910 {
    @Test
    public void testOperations() throws Exception {
        Map<String, Object> headers = new HashMap<>();
        assertFalse(gridFSBucket.find(eq(FILE_NAME)).cursor().hasNext());

        headers.put(Exchange.FILE_NAME, FILE_NAME);
        headers.put(Exchange.CONTENT_TYPE, "text/plain");
        template.requestBodyAndHeaders("direct:create", FILE_DATA, headers);
        assertTrue(gridFSBucket.find(eq(GridFsConstants.GRIDFS_FILE_KEY_FILENAME, FILE_NAME)).cursor().hasNext());
        assertEquals(1, template.requestBodyAndHeaders("direct:count", null, headers, Long.class).longValue());
        Exchange result = template.request("direct:findOne", exchange -> exchange.getMessage().setHeaders(headers));
        assertTrue(result.getMessage().getHeader(Exchange.FILE_LENGTH, Long.class) > 0);
        assertNotNull(result.getMessage().getHeader(Exchange.FILE_LAST_MODIFIED));

        InputStream ins = result.getMessage().getBody(InputStream.class);
        assertNotNull(ins);
        byte b[] = new byte[2048];
        int i = ins.read(b);
        assertEquals(FILE_DATA, new String(b, 0, i, StandardCharsets.UTF_8));

        headers.put(Exchange.FILE_NAME, "2-" + FILE_NAME);
        headers.put(GridFsEndpoint.GRIDFS_CHUNKSIZE, 10);
        headers.put(GridFsEndpoint.GRIDFS_METADATA, "{'foo': 'bar'}");

        template.requestBodyAndHeaders("direct:create", FILE_DATA + "data2", headers);
        assertEquals(1, template.requestBodyAndHeaders("direct:count", null, headers, Long.class).longValue());
        assertEquals(2, template.requestBody("direct:count", null, Long.class).longValue());

        String s = template.requestBody("direct:listAll", null, String.class);
        assertTrue(s.contains("2-" + FILE_NAME));
        template.requestBodyAndHeaders("direct:remove", null, headers);
        assertEquals(1, template.requestBody("direct:count", null, Long.class).longValue());
        s = template.requestBodyAndHeader("direct:listAll", null, Exchange.FILE_NAME, "2-" + FILE_NAME, String.class);
        assertFalse(s.contains("2-" + FILE_NAME));
    }

}