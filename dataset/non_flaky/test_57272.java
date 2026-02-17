class DummyClass_57272 {
  @Test
  public void testGetMetricsResponse() throws Exception {
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    ServletOutputStream outputStreamMock = mock(ServletOutputStream.class);
    when(responseMock.getOutputStream()).thenReturn(outputStreamMock);
    UriInfo uriInfoMock = mock(UriInfo.class);
    URI uriMock = mock(URI.class);
    when(uriMock.getQuery()).thenReturn("");
    when(uriInfoMock.getRequestUri()).thenReturn(uriMock);

    // Mock makeHttpCall to send a json response
    // when the prometheus endpoint is queried.
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader
        .getResourceAsStream(PROMETHEUS_TEST_RESPONSE_FILE);
    HttpURLConnection urlConnectionMock = mock(HttpURLConnection.class);
    when(urlConnectionMock.getResponseCode())
        .thenReturn(HttpServletResponse.SC_OK);
    when(urlConnectionMock.getInputStream()).thenReturn(inputStream);
    when(reconUtilsMock.makeHttpCall(any(URLConnectionFactory.class),
        anyString(), anyBoolean())).thenReturn(urlConnectionMock);

    metricsProxyEndpoint.getMetricsResponse(PROMETHEUS_INSTANT_QUERY_API,
        uriInfoMock, responseMock);

    byte[] fileBytes = FileUtils.readFileToByteArray(
        new File(classLoader.getResource(PROMETHEUS_TEST_RESPONSE_FILE)
            .getFile())
        );
    verify(outputStreamMock).write(fileBytes, 0, fileBytes.length);
  }

}