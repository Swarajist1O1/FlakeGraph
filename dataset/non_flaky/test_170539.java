class DummyClass_170539 {
    @Test
    public void test() throws Exception
    {
        //SCI with @HandlesTypes[Ordinary, Sample]
        SampleServletContainerInitializer sci = new SampleServletContainerInitializer();
        
        DiscoveredServletContainerInitializerHolder holder = 
            new DiscoveredServletContainerInitializerHolder(new Source(Source.Origin.ANNOTATION, sci.getClass().getName()),
            sci);

        //add the @HandlesTypes to the holder
        holder.addStartupClasses(Ordinary.class, Sample.class);
        
        //pretend scanned and discovered that ASample has the Sample annotation
        holder.addStartupClasses(ASample.class.getName());
        
        //pretend we scanned the entire class hierarchy and found:
        //   com.acme.tom and com.acme.dick both extend Ordinary
        //   ASample has subclass BSample
        Map<String, Set<String>> classMap = new HashMap<>();
        classMap.put(Ordinary.class.getName(), new HashSet(Arrays.asList("com.acme.tom", "com.acme.dick")));
        classMap.put(ASample.class.getName(), new HashSet(Arrays.asList(BSample.class.getName())));
        holder.resolveClasses(classMap);
        
        //we should now have the following classes that will be passed to the SampleServletContainerInitializer.onStartup
        String toString = holder.toString();
        assertThat(toString, containsString("com.acme.tom"));
        assertThat(toString, containsString("com.acme.dick"));
        assertThat(toString, containsString(ASample.class.getName()));
        assertThat(toString, containsString(BSample.class.getName()));
        assertThat(toString, containsString("applicable=[],annotated=[]"));
    }

}