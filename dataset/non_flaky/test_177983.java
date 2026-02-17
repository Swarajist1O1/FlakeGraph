class DummyClass_177983 {
    @Test
    public void testUnicodeWrap() {
        // Make sure an input of null doesn't crash anything.
        assertNull(LTR_FMT.unicodeWrap(null));

        // Uniform directionality in opposite context.
        assertEquals("uniform dir opposite to LTR context",
                RLE + "." + HE + "." + PDF + LRM,
                LTR_FMT_EXIT_RESET.unicodeWrap("." + HE + "."));
        assertEquals("uniform dir opposite to LTR context, stereo reset",
                LRM + RLE + "." + HE + "." + PDF + LRM,
                LTR_FMT.unicodeWrap("." + HE + "."));
        assertEquals("uniform dir opposite to LTR context, stereo reset, no isolation",
                RLE + "." + HE + "." + PDF,
                LTR_FMT.unicodeWrap("." + HE + ".", false));
        assertEquals("neutral treated as opposite to LTR context",
                RLE + "." + PDF + LRM,
                LTR_FMT_EXIT_RESET.unicodeWrap(".", TextDirectionHeuristicsCompat.RTL));
        assertEquals("uniform dir opposite to RTL context",
                LRE + "." + EN + "." + PDF + RLM,
                RTL_FMT_EXIT_RESET.unicodeWrap("." + EN + "."));
        assertEquals("uniform dir opposite to RTL context, stereo reset",
                RLM + LRE + "." + EN + "." + PDF + RLM,
                RTL_FMT.unicodeWrap("." + EN + "."));
        assertEquals("uniform dir opposite to RTL context, stereo reset, no isolation",
                LRE + "." + EN + "." + PDF,
                RTL_FMT.unicodeWrap("." + EN + ".", false));
        assertEquals("neutral treated as opposite to RTL context",
                LRE + "." + PDF + RLM,
                RTL_FMT_EXIT_RESET.unicodeWrap(".", TextDirectionHeuristicsCompat.LTR));

        // We test mixed-directionality cases only with an explicit overall directionality parameter
        // because the estimation logic is outside the sphere of BidiFormatter, and different
        // estimators will treat them differently.

        // Overall directionality matching context, but with opposite exit directionality.
        assertEquals("exit dir opposite to LTR context",
                EN + HE + LRM,
                LTR_FMT_EXIT_RESET.unicodeWrap(EN + HE, TextDirectionHeuristicsCompat.LTR));
        assertEquals("exit dir opposite to LTR context, stereo reset",
                EN + HE + LRM,
                LTR_FMT.unicodeWrap(EN + HE, TextDirectionHeuristicsCompat.LTR));
        assertEquals("exit dir opposite to LTR context, stereo reset, no isolation",
                EN + HE,
                LTR_FMT.unicodeWrap(EN + HE, TextDirectionHeuristicsCompat.LTR, false));

        assertEquals("exit dir opposite to RTL context",
                HE + EN + RLM,
                RTL_FMT_EXIT_RESET.unicodeWrap(HE + EN, TextDirectionHeuristicsCompat.RTL));
        assertEquals("exit dir opposite to RTL context, stereo reset",
                HE + EN + RLM,
                RTL_FMT.unicodeWrap(HE + EN, TextDirectionHeuristicsCompat.RTL));
        assertEquals("exit dir opposite to RTL context, stereo reset, no isolation",
                HE + EN,
                RTL_FMT.unicodeWrap(HE + EN, TextDirectionHeuristicsCompat.RTL, false));

        // Overall directionality matching context, but with opposite entry directionality.
        assertEquals("entry dir opposite to LTR context",
                HE + EN,
                LTR_FMT_EXIT_RESET.unicodeWrap(HE + EN, TextDirectionHeuristicsCompat.LTR));
        assertEquals("entry dir opposite to LTR context, stereo reset",
                LRM + HE + EN,
                LTR_FMT.unicodeWrap(HE + EN, TextDirectionHeuristicsCompat.LTR));
        assertEquals("entry dir opposite to LTR context, stereo reset, no isolation",
                HE + EN,
                LTR_FMT.unicodeWrap(HE + EN, TextDirectionHeuristicsCompat.LTR, false));

        assertEquals("entry dir opposite to RTL context",
                EN + HE,
                RTL_FMT_EXIT_RESET.unicodeWrap(EN + HE, TextDirectionHeuristicsCompat.RTL));
        assertEquals("entry dir opposite to RTL context, stereo reset",
                RLM + EN + HE,
                RTL_FMT.unicodeWrap(EN + HE, TextDirectionHeuristicsCompat.RTL));
        assertEquals("entry dir opposite to RTL context, stereo reset, no isolation",
                EN + HE,
                RTL_FMT.unicodeWrap(EN + HE, TextDirectionHeuristicsCompat.RTL, false));

        // Overall directionality matching context, but with opposite entry and exit directionality.
        assertEquals("entry and exit dir opposite to LTR context",
                HE + EN + HE + LRM,
                LTR_FMT_EXIT_RESET.unicodeWrap(HE + EN + HE, TextDirectionHeuristicsCompat.LTR));
        assertEquals("entry and exit dir opposite to LTR context, stereo reset",
                LRM + HE + EN + HE + LRM,
                LTR_FMT.unicodeWrap(HE + EN + HE, TextDirectionHeuristicsCompat.LTR));
        assertEquals("entry and exit dir opposite to LTR context, no isolation",
                HE + EN + HE,
                LTR_FMT_EXIT_RESET.unicodeWrap(HE + EN + HE, TextDirectionHeuristicsCompat.LTR,
                        false));

        assertEquals("entry and exit dir opposite to RTL context",
                EN + HE + EN + RLM,
                RTL_FMT_EXIT_RESET.unicodeWrap(EN + HE + EN, TextDirectionHeuristicsCompat.RTL));
        assertEquals("entry and exit dir opposite to RTL context, no isolation",
                EN + HE + EN,
                RTL_FMT_EXIT_RESET.unicodeWrap(EN + HE + EN, TextDirectionHeuristicsCompat.RTL,
                        false));

        // Entry and exit directionality matching context, but with opposite overall directionality.
        assertEquals("overall dir (but not entry or exit dir) opposite to LTR context",
                RLE + EN + HE + EN + PDF + LRM,
                LTR_FMT_EXIT_RESET.unicodeWrap(EN + HE + EN, TextDirectionHeuristicsCompat.RTL));
        assertEquals("overall dir (but not entry or exit dir) opposite to LTR context, stereo reset",
                LRM + RLE + EN + HE + EN + PDF + LRM,
                LTR_FMT.unicodeWrap(EN + HE + EN, TextDirectionHeuristicsCompat.RTL));
        assertEquals("overall dir (but not entry or exit dir) opposite to LTR context, no isolation",
                RLE + EN + HE + EN + PDF,
                LTR_FMT_EXIT_RESET.unicodeWrap(EN + HE + EN, TextDirectionHeuristicsCompat.RTL,
                        false));

        assertEquals("overall dir (but not entry or exit dir) opposite to RTL context",
                LRE + HE + EN + HE + PDF + RLM,
                RTL_FMT_EXIT_RESET.unicodeWrap(HE + EN + HE, TextDirectionHeuristicsCompat.LTR));
        assertEquals("overall dir (but not entry or exit dir) opposite to RTL context, stereo reset",
                RLM + LRE + HE + EN + HE + PDF + RLM,
                RTL_FMT.unicodeWrap(HE + EN + HE, TextDirectionHeuristicsCompat.LTR));
        assertEquals("overall dir (but not entry or exit dir) opposite to RTL context, no isolation",
                LRE + HE + EN + HE + PDF,
                RTL_FMT_EXIT_RESET.unicodeWrap(HE + EN + HE, TextDirectionHeuristicsCompat.LTR,
                        false));
    }

}