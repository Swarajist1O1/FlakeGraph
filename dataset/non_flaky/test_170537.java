class DummyClass_170537 {
    @Test
    public void testTypeInheritanceHandling() throws Exception
    {
        Map<String, Set<String>> map = new ConcurrentHashMap<>();

        AnnotationParser parser = new AnnotationParser();
        ClassInheritanceHandler handler = new ClassInheritanceHandler(map);

        class Foo implements InterfaceD
        {
        }

        classNames.clear();
        classNames.add(ClassA.class.getName());
        classNames.add(ClassB.class.getName());
        classNames.add(InterfaceD.class.getName());
        classNames.add(Foo.class.getName());

        parser.parse(Collections.singleton(handler), classNames);

        assertNotNull(map);
        assertFalse(map.isEmpty());
        assertEquals(2, map.size());

        assertThat(map, hasKey("org.eclipse.jetty.annotations.ClassA"));
        assertThat(map, hasKey("org.eclipse.jetty.annotations.InterfaceD"));
        Set<String> classes = map.get("org.eclipse.jetty.annotations.ClassA");
        assertThat(classes, contains("org.eclipse.jetty.annotations.ClassB"));

        classes = map.get("org.eclipse.jetty.annotations.InterfaceD");
        assertThat(classes, containsInAnyOrder("org.eclipse.jetty.annotations.ClassB",
            Foo.class.getName()));
    }

}