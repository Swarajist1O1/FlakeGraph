class DummyClass_98578 {
    @Test
    public void test_speed() throws SecurityException, NoSuchMethodException {
        final SimpleSpeedTest z = new SimpleSpeedTest();
        final String elstr = "num + (i - 1 + 2 - 3 + 4 - 5 + 6 - 7)-z.abc(i)";
        final Context context = Lang.context("{num:0}");
        context.set("z", z);

        System.out.println("\n" + Strings.dup('=', 100));

        Stopwatch sw = Stopwatch.run(new Atom() {
            public void run() {
                int num = 0;
                for (int i = 0; i < max; i++)
                    num = num + (i - 1 + 2 - 3 + 4 - 5 + 6 - 7) - z.abc(i);
                //System.out.println("Num: " + num);
            }

}