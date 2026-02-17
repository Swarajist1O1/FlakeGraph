class DummyClass_98604 {
    @Test
    public void complexOperation() {
        assertEquals(1000
                     + 100.0
                     * 99
                     - (600 - 3 * 15)
                     % (((68 - 9) - 3) * 2 - 100)
                     + 10000
                     % 7
                     * 71, El.eval("1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71"));
        assertEquals(6.7 - 100 > 39.6 ? true ? 4 + 5 : 6 - 1 : !(100 % 3 - 39.0 < 27) ? 8 * 2 - 199
                                                                                     : 100 % 3,
                     El.eval("6.7-100>39.6 ? 5==5? 4+5:6-1 : !(100%3-39.0<27) ? 8*2-199: 100%3"));

        Context vars = Lang.context();
        vars.set("i", 100);
        vars.set("pi", 3.14f);
        vars.set("d", -3.9);
        vars.set("b", (byte) 4);
        vars.set("bool", false);
        vars.set("t", "");
        String t = "i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99 ==i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99";
        // t =
        // "i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99";
        assertEquals(true, El.eval(vars, t));

        // assertEquals('A' == ('A') || 'B' == 'B' && "ABCD" == "" && 'A' ==
        // 'A', el.eval(vars,
        // "'A' == 'A' || 'B' == 'B' && 'ABCD' == t &&  'A' == 'A'"));
        assertEquals(true || true && false && true,
                     El.eval(vars, "'A' == 'A' || 'B' == 'B' && 'ABCD' == t &&  'A' == 'A'"));
    }

}