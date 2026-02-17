class DummyClass_84584 {
    @Test
    public void testOrderWithSamePrefix() throws Exception {
        final String[] names = {"x-3", "x-5", "x-11", "x-1"};
        ZNodeName zname;

        final Collection<ZNodeName> nodeNames = Arrays.asList(names).stream()
            .map(name -> new ZNodeName(name)).sorted().collect(Collectors.toList());

        final Iterator<ZNodeName> it = nodeNames.iterator();

        zname = it.next();
        assertEquals("x-1", zname.getName());
        assertEquals("x", zname.getPrefix());
        assertEquals(Integer.valueOf(1), zname.getSequence().get());

        zname = it.next();
        assertEquals("x-3", zname.getName());
        assertEquals("x", zname.getPrefix());
        assertEquals(Integer.valueOf(3), zname.getSequence().get());

        zname = it.next();
        assertEquals("x-5", zname.getName());
        assertEquals("x", zname.getPrefix());
        assertEquals(Integer.valueOf(5), zname.getSequence().get());

        zname = it.next();
        assertEquals("x-11", zname.getName());
        assertEquals("x", zname.getPrefix());
        assertEquals(Integer.valueOf(11), zname.getSequence().get());
    }

}