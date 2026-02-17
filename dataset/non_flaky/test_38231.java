class DummyClass_38231 {
    @Test
    public void testEscapeHtmlBasic() {
        String input1 = "\"A\" \"b\"; 1 < 2 && 3 > 2";
        String output1 = "&quot;A&quot; &quot;b&quot;; 1 &lt; 2 &amp;&amp; 3 &gt; 2";
        assertTrue(output1.equals(TextUtils.escapeHtml(input1)));
    }

}