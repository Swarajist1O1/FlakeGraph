class DummyClass_77583 {
    @Test
            public List<Proxy> select(URI uri) {
                return Collections.singletonList(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.53.12", 8080)));
            }

}