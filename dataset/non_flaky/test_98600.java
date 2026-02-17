class DummyClass_98600 {
    @Test
    public void speed() {
        SimpleSpeedTest z = new SimpleSpeedTest();
        int num = 4988;
        String elstr = "num + (i - 1 + 2 - 3 + 4 - 5 + 6 - 7)-z.abc(i)";
        int i = 5000;
        Context con = Lang.context();
        con.set("num", num);
        con.set("i", i);
        con.set("z", z);
        assertEquals(num + (i - 1 + 2 - 3 + 4 - 5 + 6 - 7) - z.abc(i), El.eval(con, elstr));
    }

}