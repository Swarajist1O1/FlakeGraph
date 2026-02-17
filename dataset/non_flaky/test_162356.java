class DummyClass_162356 {
    @Test
    public void testJSONSerialize() throws IOException {
        SimpleProgressMonitor monitor = new SimpleProgressMonitor();
        monitor.setState(TaskState.Running);
        monitor.setProgress(42);
//        monitor.setSuccess(true);
        monitor.report("message 1");
        monitor.report("message 2");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JSFullWriter out = new JSFullWriter(bos);
        monitor.writeTo(out);
        out.finishOutput();
        
        String serialization = bos.toString();
//        System.out.println(serialization);
        
        JsonObject object = JSON.parse( serialization );
        assertEquals( 42,       object.get("progress").getAsNumber().value().intValue());
        assertEquals("Running", object.get("state").getAsString().value());
        assertEquals( true,     object.get("succeeded").getAsBoolean().value());
        JsonArray messages = object.get("messages").getAsArray();
        assertEquals( 2,        messages.size());
        JsonObject m = messages.get(1).getAsObject();
        assertEquals( "message 2",   m.get("message").getAsString().value());
    }

}