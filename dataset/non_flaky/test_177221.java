class DummyClass_177221 {
    @Test
    public void testGetEndpointGroupName() throws Exception {
        assertNull(getEndpointGroupName("http://myGroupName/"));
        assertNull(getEndpointGroupName("http://myGroupName:8080/xxx"));
        assertNull(getEndpointGroupName("http://group1:myGroupName:8080/"));
        assertNull(getEndpointGroupName("http://username:password@myGroupName:8080/"));

        assertEquals("myGroupName", getEndpointGroupName("http://" + endpointGroupMark + "myGroupName/"));
        assertEquals("myGroupName", getEndpointGroupName("http://" + endpointGroupMark + "myGroupName:8080/"));
        assertEquals("myGroupName", getEndpointGroupName("http://" + endpointGroupMark + "myGroupName:8080/"));
        assertEquals("myGroupName", getEndpointGroupName("http://username:password@" + endpointGroupMark +
                                                         "myGroupName:8080/"));
    }

}