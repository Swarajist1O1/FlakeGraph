class DummyClass_98615 {
    @Test
    public void testIssue308(){
        Context context = Lang.context();
        List<String> list = new ArrayList<String>();
        list.add("jk");
        context.set("list", list);
        context.set("System", System.class);
        
        El.eval(context, "System.getenv('PATH').getClass().getName()");
        assertEquals("1", Mirror.me(String.class).invoke(String.class, "valueOf", 1));
        
        assertEquals("jk", Mirror.me(String.class).invoke(String.class, "valueOf", "jk"));
    }

}