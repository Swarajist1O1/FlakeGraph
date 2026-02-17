class DummyClass_98366 {
    @Test
            public void onRequest(HttpRequest<?> request, Config config) {
                request.getBody().ifPresent(b ->
                        b.multiParts().forEach(part ->
                                values.add(part.toString())));
            }

}