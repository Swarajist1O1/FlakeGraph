class DummyClass_84587 {
    @Test
    public void testOrderWithExtraPrefixes() throws Exception {
        String[] names = {"r-1-3-2", "r-2-2-1", "r-3-1-3"};
        ZNodeName zname;

        final Collection<ZNodeName> nodeNames = Arrays.asList(names).stream()
            .map(name -> new ZNodeName(name)).sorted().collect(Collectors.toList());

        final Iterator<ZNodeName> it = nodeNames.iterator();

        zname = it.next();
        assertEquals("r-2-2-1", zname.getName());
        assertEquals("r-2-2", zname.getPrefix());
        assertEquals(Integer.valueOf(1), zname.getSequence().get());

        zname = it.next();
        assertEquals("r-1-3-2", zname.getName());
        assertEquals("r-1-3", zname.getPrefix());
        assertEquals(Integer.valueOf(2), zname.getSequence().get());

        zname = it.next();
        assertEquals("r-3-1-3", zname.getName());
        assertEquals("r-3-1", zname.getPrefix());
        assertEquals(Integer.valueOf(3), zname.getSequence().get());
    }

}