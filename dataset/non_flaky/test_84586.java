class DummyClass_84586 {
    @Test
    public void testOrderWithDifferentPrefixIncludingSessionId() throws Exception {
        String[] names = {
            "x-242681582799028564-0000000002",
            "x-170623981976748329-0000000003",
            "x-98566387950223723-0000000001"
        };
        ZNodeName zname;

        final Collection<ZNodeName> nodeNames = Arrays.asList(names).stream()
            .map(name -> new ZNodeName(name)).sorted().collect(Collectors.toList());

        final Iterator<ZNodeName> it = nodeNames.iterator();

        zname = it.next();
        assertEquals("x-98566387950223723-0000000001", zname.getName());
        assertEquals("x-98566387950223723", zname.getPrefix());
        assertEquals(Integer.valueOf(1), zname.getSequence().get());

        zname = it.next();
        assertEquals("x-242681582799028564-0000000002", zname.getName());
        assertEquals("x-242681582799028564", zname.getPrefix());
        assertEquals(Integer.valueOf(2), zname.getSequence().get());

        zname = it.next();
        assertEquals("x-170623981976748329-0000000003", zname.getName());
        assertEquals("x-170623981976748329", zname.getPrefix());
        assertEquals(Integer.valueOf(3), zname.getSequence().get());
    }

}