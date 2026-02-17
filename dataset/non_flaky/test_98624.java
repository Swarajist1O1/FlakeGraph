class DummyClass_98624 {
    @Test(timeout=5000, expected=Exception.class)
    public void test_el_issue1185() {
        Context context = Lang.context();
        El.eval(context, "a.b)*0.30");
    }

}