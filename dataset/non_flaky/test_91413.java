class DummyClass_91413 {
@TestLogging("org.elasticsearch.client:TRACE,tracer:TRACE")
    public void cleanExporters() throws Exception {
        Request request = new Request("PUT", "/_cluster/settings");
        request.setJsonEntity(Strings.toString(jsonBuilder().startObject()
                .startObject("transient")
                    .nullField("xpack.monitoring.exporters.*")
                .endObject().endObject()));
        adminClient().performRequest(request);
        adminClient().performRequest(new Request("DELETE", "/.watch*"));
    }

}