class DummyClass_84585 {
    @Test
    public void testOrderWithDifferentPrefixes() throws Exception {
        final String[] names = {"r-3", "r-2", "r-1", "w-2", "w-1"};
        ZNodeName zname;

        final Collection<ZNodeName> nodeNames = Arrays.asList(names).stream()
            .map(name -> new ZNodeName(name)).sorted().collect(Collectors.toList());

        final Iterator<ZNodeName> it = nodeNames.iterator();

        zname = it.next();
        assertEquals("r-1", zname.getName());
        assertEquals("r", zname.getPrefix());
        assertEquals(Integer.valueOf(1), zname.getSequence().get());

        zname = it.next();
        assertEquals("w-1", zname.getName());
        assertEquals("w", zname.getPrefix());
        assertEquals(Integer.valueOf(1), zname.getSequence().get());

        zname = it.next();
        assertEquals("r-2", zname.getName());
        assertEquals("r", zname.getPrefix());
        assertEquals(Integer.valueOf(2), zname.getSequence().get());

        zname = it.next();
        assertEquals("w-2", zname.getName());
        assertEquals("w", zname.getPrefix());
        assertEquals(Integer.valueOf(2), zname.getSequence().get());

        zname = it.next();
        assertEquals("r-3", zname.getName());
        assertEquals("r", zname.getPrefix());
        assertEquals(Integer.valueOf(3), zname.getSequence().get());
    }

}