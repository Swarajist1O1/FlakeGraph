class DummyClass_98614 {
    @Test
    public void testIssue307(){
        Context context = Lang.context();
        List<String> list = new ArrayList<String>();
        list.add("jk");
        context.set("list", list);
        context.set("System", System.class);
        
        El.eval(context, "list.add(list.get(0))");
        assertEquals(2, list.size());
    }

}