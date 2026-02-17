class DummyClass_98626 {
    @Test
    public void test_issue_1229() {
        Context ctx = Lang.context();
        ctx.set("obj", new NutMap("pet", null).setv("girls", new ArrayList<String>()));
        El.eval(ctx, "obj.pet");
        El.eval(ctx, "!!(obj.pet)");
        assertTrue((Boolean)El.eval(ctx, "!!(obj.pet.name) == null"));
        assertTrue((Boolean)El.eval(ctx, "!(!(!!(obj.pet.name) == null))"));
        assertEquals("wendal", El.eval(ctx, "!!(obj.pet.name) ||| 'wendal'"));
        assertEquals("dog", El.eval(ctx, "!!(obj.girls) ||| 'dog'"));
    }

}