class DummyClass_98627 {
    @Test
    public void test_issue_1475_1476() {
        
        Context context = Lang.context();
        context.set("Math", Math.class);
        
        
        //Queue<Object> rpn = new ShuntingYard().parseToRPN("Math.max(10, 0-11)");
        //System.out.println(rpn);
        
//        Queue<Object> rpn = new ShuntingYard().parseToRPN("Math.max(0,-10)");
//        System.out.println(rpn);
        Object max = El.eval(context, "Math.max(0,-11)");
        assertEquals(0, max);
        
        
        assertEquals(0, El.eval(context, "Math.max(-1,0)"));
        assertEquals(0, El.eval(context, "Math.max(0,-1)"));
        assertEquals(0, El.eval(context, "Math.max(-0,-1)"));
        

        assertEquals(0, El.eval(context, "Math.max(-1,Math.max(-1,Math.max(-1,Math.max(-1,0))))"));
        assertEquals(0, El.eval(context, "Math.max(Math.max(Math.max(Math.max(0,-1),-1),-1),-1)"));
        assertEquals(0, El.eval(context, "Math.max(-Math.max(-Math.max(-Math.max(-0,-1),-1),-1),-1)"));
    }

}