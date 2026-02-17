class DummyClass_99738 {
    @Test
    public void testParser() {
        QueryReplayer.ParsedTargetHost pth;
        pth = fromString("127.0.0.1");
        assertEquals("127.0.0.1", pth.host);
        assertEquals(9042, pth.port );
        assertNull(pth.user);
        assertNull(pth.password);

        pth = fromString("127.0.0.1:3333");
        assertEquals("127.0.0.1", pth.host);
        assertEquals(3333, pth.port );
        assertNull(pth.user);
        assertNull(pth.password);

        pth = fromString("aaa:bbb@127.0.0.1:3333");
        assertEquals("127.0.0.1", pth.host);
        assertEquals(3333, pth.port );
        assertEquals("aaa", pth.user);
        assertEquals("bbb", pth.password);

        pth = fromString("aaa:bbb@127.0.0.1");
        assertEquals("127.0.0.1", pth.host);
        assertEquals(9042, pth.port );
        assertEquals("aaa", pth.user);
        assertEquals("bbb", pth.password);
    }

}