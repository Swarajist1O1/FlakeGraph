class DummyClass_177220 {
    @Test
            public Endpoint selectNow(ClientRequestContext ctx) {
                final List<Endpoint> endpoints = endpointGroup.endpoints();
                return endpoints.isEmpty() ? null : endpoints.get(0);
            }

}