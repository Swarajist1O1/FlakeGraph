class DummyClass_53129 {
    @Test
    public void testListDomain() throws Exception {
        Set<ObjectName> names = server.queryNames(ObjectName.getInstance("java.lang:type=Runtime,*"), null);
        System.err.println(names);
        assertTrue(names.size() == 1);
        MBeanMap result = new MBeanMap(server, names.iterator().next());
        @SuppressWarnings("unchecked")
        Map<String,String>  properties = (Map<String, String>) result.get("system_properties");
        assertTrue(properties.containsKey("java.vm.version"));
    }

}