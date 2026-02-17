class DummyClass_38217 {
    @Test
    public void testProperCaseWords() throws Exception {
        String[] words = new String[] { "AA102", "nw", "dog", "daVID CHiu", "yu-gi-oh rules",
                "b.j. penn the great,shawn sherk"};
        String[] results = new String[] { "AA102", "Nw", "Dog", "David Chiu", "Yu-Gi-Oh Rules",
                "B.J. Penn The Great,Shawn Sherk"};
        for (int i=0; i < words.length; i++) {
            String result = TextUtils.properCaseWords(words[i]);
            assertEquals(results[i], result);
        }
    }

}