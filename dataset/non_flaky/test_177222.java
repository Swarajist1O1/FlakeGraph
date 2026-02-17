class DummyClass_177222 {
    @Test
    public void testReplace() throws Exception {
        final String replacement = "127.0.0.1:1234";
        assertEquals("http://myGroupName/",
                     replaceEndpointGroup("http://myGroupName/", replacement));
        assertEquals("http://myGroupName:8080/xxx",
                     replaceEndpointGroup("http://myGroupName:8080/xxx", replacement));
        assertEquals("http://group1:myGroupName:8080/",
                     replaceEndpointGroup("http://group1:myGroupName:8080/", replacement));
        assertEquals("http://username:password@myGroupName:8080/",
                     replaceEndpointGroup("http://username:password@myGroupName:8080/", replacement));

        assertEquals("http://127.0.0.1:1234/",
                     replaceEndpointGroup("http://" + endpointGroupMark + "myGroupName/", replacement));
        assertEquals("http://127.0.0.1:1234/",
                     replaceEndpointGroup("http://" + endpointGroupMark + "myGroupName:8080/", replacement));
        assertEquals("http://127.0.0.1:1234/xxx",
                     replaceEndpointGroup("http://" + endpointGroupMark + "myGroupName:8080/xxx", replacement));
        assertEquals("http://username:password@127.0.0.1:1234/xxx",
                     replaceEndpointGroup("http://username:password@" + endpointGroupMark +
                                          "myGroupName:8080/xxx", replacement));
    }

}