class DummyClass_177984 {
    @Test
    public void testCharSequenceApis() {
        final CharSequence CS_HE = new SpannableString(HE);
        assertEquals(true, BidiFormatter.getInstance(true).isRtl(CS_HE));

        final SpannableString CS_EN_HE = new SpannableString(EN + HE);
        final Object RELATIVE_SIZE_SPAN = new RelativeSizeSpan(1.2f);
        CS_EN_HE.setSpan(RELATIVE_SIZE_SPAN, 0, EN.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        Spanned wrapped;
        Object[] spans;

        wrapped = (Spanned) LTR_FMT.unicodeWrap(CS_EN_HE);
        assertEquals(EN + HE + LRM, wrapped.toString());
        spans = wrapped.getSpans(0, wrapped.length(), Object.class);
        assertEquals(1, spans.length);
        assertEquals(RELATIVE_SIZE_SPAN, spans[0]);
        assertEquals(0, wrapped.getSpanStart(RELATIVE_SIZE_SPAN));
        assertEquals(EN.length(), wrapped.getSpanEnd(RELATIVE_SIZE_SPAN));

        wrapped = (Spanned) LTR_FMT.unicodeWrap(CS_EN_HE, TextDirectionHeuristicsCompat.LTR);
        assertEquals(EN + HE + LRM, wrapped.toString());
        spans = wrapped.getSpans(0, wrapped.length(), Object.class);
        assertEquals(1, spans.length);
        assertEquals(RELATIVE_SIZE_SPAN, spans[0]);
        assertEquals(0, wrapped.getSpanStart(RELATIVE_SIZE_SPAN));
        assertEquals(EN.length(), wrapped.getSpanEnd(RELATIVE_SIZE_SPAN));

        wrapped = (Spanned) LTR_FMT.unicodeWrap(CS_EN_HE, false);
        assertEquals(EN + HE, wrapped.toString());
        spans = wrapped.getSpans(0, wrapped.length(), Object.class);
        assertEquals(1, spans.length);
        assertEquals(RELATIVE_SIZE_SPAN, spans[0]);
        assertEquals(0, wrapped.getSpanStart(RELATIVE_SIZE_SPAN));
        assertEquals(EN.length(), wrapped.getSpanEnd(RELATIVE_SIZE_SPAN));

        wrapped = (Spanned) LTR_FMT.unicodeWrap(CS_EN_HE, TextDirectionHeuristicsCompat.LTR, false);
        assertEquals(EN + HE, wrapped.toString());
        spans = wrapped.getSpans(0, wrapped.length(), Object.class);
        assertEquals(1, spans.length);
        assertEquals(RELATIVE_SIZE_SPAN, spans[0]);
        assertEquals(0, wrapped.getSpanStart(RELATIVE_SIZE_SPAN));
        assertEquals(EN.length(), wrapped.getSpanEnd(RELATIVE_SIZE_SPAN));
    }

}