class DummyClass_91414 {
@TestLogging("org.elasticsearch.client:TRACE")
    public void waitForSecuritySetup() throws Exception {

        String masterNode = null;
        String catNodesResponse = EntityUtils.toString(
                client().performRequest("GET", "/_cat/nodes?h=id,master").getEntity(),
                StandardCharsets.UTF_8
        );
        for (String line : catNodesResponse.split("\n")) {
            int indexOfStar = line.indexOf('*'); // * in the node's output denotes it is master
            if (indexOfStar != -1) {
                masterNode = line.substring(0, indexOfStar).trim();
                break;
            }
        }
        assertNotNull(masterNode);
        final String masterNodeId = masterNode;

        assertBusy(() -> {
            try {
                Response nodeDetailsResponse = client().performRequest("GET", "/_nodes");
                ObjectPath path = ObjectPath.createFromResponse(nodeDetailsResponse);
                Map<String, Object> nodes = path.evaluate("nodes");
                assertThat(nodes.size(), greaterThanOrEqualTo(2));
                String masterVersion = null;
                for (String key : nodes.keySet()) {
                    // get the ES version number master is on
                    if (key.startsWith(masterNodeId)) {
                        masterVersion = path.evaluate("nodes." + key + ".version");
                        break;
                    }
                }
                assertNotNull(masterVersion);
                final String masterTemplateVersion = masterVersion;

                Response response = client().performRequest("GET", "/_cluster/state/metadata");
                ObjectPath objectPath = ObjectPath.createFromResponse(response);
                final String mappingsPath = "metadata.templates.security-index-template.mappings";
                Map<String, Object> mappings = objectPath.evaluate(mappingsPath);
                assertNotNull(mappings);
                assertThat(mappings.size(), greaterThanOrEqualTo(1));
                for (String key : mappings.keySet()) {
                    String templateVersion = objectPath.evaluate(mappingsPath + "." + key + "" +
                            "._meta.security-version");
                    final Version mVersion = Version.fromString(masterTemplateVersion);
                    final Version tVersion = Version.fromString(templateVersion);
                    assertEquals(mVersion, tVersion);
                }
            } catch (Exception e) {
                throw new AssertionError("failed to get cluster state", e);
            }
        });

        nodes = buildNodeAndVersions();
        logger.info("Nodes in cluster before test: bwc [{}], new [{}], master [{}]", nodes.getBWCNodes(), nodes.getNewNodes(),
                nodes.getMaster());

        Map<String, String> params = Collections.singletonMap("error_trace", "true");
        executeAgainstMasterNode(client -> {
            // create a watch before each test, most of the time this is just overwriting...
            assertOK(client.performRequest("PUT", "/_xpack/watcher/watch/my-watch", params, entity));
            // just a check to see if we can execute a watch, purely optional
            if (randomBoolean()) {
                assertOK(client.performRequest("POST", "/_xpack/watcher/watch/my-watch/_execute", params,
                        new StringEntity("{ \"record_execution\" : true }", ContentType.APPLICATION_JSON)));
            }
            if (randomBoolean()) {
                Map<String, String> ignore404Params = MapBuilder.newMapBuilder(params).put("ignore", "404").immutableMap();
                Response indexExistsResponse = client.performRequest("HEAD", "/.triggered_watches", ignore404Params);
                if (indexExistsResponse.getStatusLine().getStatusCode() == 404) {
                    logger.info("Created triggered watches index to ensure it gets upgraded");
                    client.performRequest("PUT", "/.triggered_watches");
                }
            }
        });

        // helping debugging output
        executeAgainstMasterNode(client -> {
            Map<String, String> filterPathParams = MapBuilder.newMapBuilder(params)
                    .put("filter_path", "*.template,*.index_patterns").immutableMap();
            Response r = client.performRequest("GET", "_template/*watch*", filterPathParams);
            logger.info("existing watcher templates response [{}]", EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8));
        });

        // set logging to debug
//        executeAgainstMasterNode(client -> {
//            StringEntity entity = new StringEntity("{ \"transient\" : { \"logger.org.elasticsearch.xpack.watcher\" : \"TRACE\" } }",
//                    ContentType.APPLICATION_JSON);
//            Response response = client.performRequest("PUT", "_cluster/settings", params, entity);
//            logger.info("cluster update settings response [{}]", EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
//        });
    }

}