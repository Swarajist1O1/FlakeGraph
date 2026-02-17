class DummyClass_162587 {
  @ParameterizedTest
  public void testHelloRequest(String service) {
    String soapMessage =
        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hel=\"http://opentelemetry.io/test/hello-web-service\">"
            + "   <soapenv:Header/>"
            + "   <soapenv:Body>"
            + "      <hel:helloRequest>"
            + "         <name>Test</name>"
            + "      </hel:helloRequest>"
            + "   </soapenv:Body>"
            + "</soapenv:Envelope>";

    AggregatedHttpResponse response =
        client.post(getAddress(service), soapMessage).aggregate().join();
    Document doc = Jsoup.parse(response.contentUtf8());

    assertThat(response.status().code()).isEqualTo(200);
    assertThat(doc.selectFirst("message").text()).isEqualTo("Hello Test");

    String methodName = "hello";
    testing.waitAndAssertTraces(
        trace ->
            trace.hasSpansSatisfyingExactly(
                span -> assertServerSpan(span, serverSpanName(service, methodName)).hasNoParent(),
                span -> assertHandlerSpan(span, service, methodName).hasParent(trace.getSpan(0)),
                span ->
                    assertAnnotationHandlerSpan(span, service, methodName)
                        .hasParent(trace.getSpan(1))));
  }

}