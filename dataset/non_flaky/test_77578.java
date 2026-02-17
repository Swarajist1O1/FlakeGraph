class DummyClass_77578 {
    @Test
            public List<Proxy> select(URI uri) {
                return Collections.singletonList(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.52.1", 8080)));
            }

}